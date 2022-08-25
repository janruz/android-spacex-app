package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.repositories.RocketsRepository
import com.github.janruz.spacexapp.utilities.Status
import com.github.janruz.spacexapp.utilities.loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel exposing rockets data with filtering capabilities
 */
@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketsRepository: RocketsRepository
): ViewModel() {

    private val _rocketsStatus = mutableStateOf(Status.NONE)

    /**
     * The status of the task of getting rockets data
     */
    val rocketsStatus = _rocketsStatus as State<Status>

    private val _messageId = MutableSharedFlow<Int>()

    /**
     * String resource id of a message containing status information about tasks performed
     * by this viewModel, for example a message telling user they are in the offline mode.
     */
    val messageId = _messageId.asSharedFlow()

    /**
     * The collection of rockets matching given filters
     */
    val rockets = derivedStateOf {
        _allRockets.value
            .applyFiltering(
                activeFilter = activeFilter.value,
                minimumSuccessRate = successRateFilter.value
            )
    }

    /**
     * The value of the rocket activity filter
     */
    val activeFilter = mutableStateOf(RocketActiveFilter.ALL)

    /**
     * The value of the rocket minimum success rate filter
     */
    val successRateFilter = mutableStateOf(0U)

    private val _allRockets = mutableStateOf(listOf<Rocket>())

    /**
     * The collection of all the rockets available (no filters applied)
     */
    val allRockets = _allRockets as State<List<Rocket>>

    /**
     * The job of a coroutine responsible for getting the rockets data. It is used to ensure
     * that there is always only one active coroutine trying to get the rockets data.
     */
    private var getRocketsJob: Job? = null

    init {
        getRockets()
    }

    /**
     * Tries to first get the rockets from cache and then fetch more up-to-date data from the API
     */
    fun getRockets() {
        getRocketsJob?.cancel()

        getRocketsJob = viewModelScope.launch {

            _rocketsStatus.loading()

            val cacheResult = rocketsRepository.getRocketsFromCache()
            if(cacheResult.isSuccess) {
                handleSuccessResult(cacheResult)
            }

            val apiResult = rocketsRepository.fetchRockets()
            if(apiResult.isSuccess) {
                handleSuccessResult(apiResult)
            }

            when {
                cacheResult.isFailure && apiResult.isFailure -> {
                    _rocketsStatus.value = Status.FAILURE
                }
                cacheResult.isSuccess && apiResult.isFailure -> {
                    _messageId.emit(R.string.error_fetching_api_showing_data_from_cache)
                }
            }
        }
    }

    private fun handleSuccessResult(result: Result<List<Rocket>?>) {
        result.getOrNull()?.let {
            _allRockets.value = it
            _rocketsStatus.value = Status.SUCCESS
        }
    }
}

/**
 * Describes all the possible values for filtering rockets by their activity
 */
enum class RocketActiveFilter {
    /**
     * Show only active rockets
     */
    ACTIVE,

    /**
     * Show only inactive rockets
     */
    INACTIVE,

    /**
     * Show all the rockets
     */
    ALL
}

/**
 * Helper function filtering the list of rockets by both activity and minimum success rate
 */
fun List<Rocket>.applyFiltering(
    activeFilter: RocketActiveFilter,
    minimumSuccessRate: UInt
): List<Rocket> {
    return filter { rocket ->
        val activityPass = when(activeFilter) {
            RocketActiveFilter.ACTIVE -> rocket.active
            RocketActiveFilter.INACTIVE -> !rocket.active
            RocketActiveFilter.ALL -> true
        }

        val successRatePass = rocket.successRate >= minimumSuccessRate

        activityPass && successRatePass
    }
}