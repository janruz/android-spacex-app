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

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketsRepository: RocketsRepository
): ViewModel() {

    private val _rocketsStatus = mutableStateOf(Status.NONE)
    val rocketsStatus = _rocketsStatus as State<Status>

    private val _messageId = MutableSharedFlow<Int>()
    val messageId = _messageId.asSharedFlow()

    val rockets = derivedStateOf {
        _allRockets.value
            .applyFiltering(
                activeFilter = activeFilter.value,
                minimumSuccessRate = successRateFilter.value
            )
    }
    val activeFilter = mutableStateOf(RocketActiveFilter.ALL)
    val successRateFilter = mutableStateOf(0U)

    private val _allRockets = mutableStateOf(listOf<Rocket>())
    val allRockets = _allRockets as State<List<Rocket>>

    private var getRocketsJob: Job? = null

    init {
        getRockets()
    }

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

enum class RocketActiveFilter {
    ACTIVE, INACTIVE, ALL
}

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