package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.repositories.RocketsRepository
import com.github.janruz.spacexapp.utilities.Status
import com.github.janruz.spacexapp.utilities.ifSuccessGetOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketsRepository: RocketsRepository
): ViewModel() {

    private val _rocketsStatus = mutableStateOf(Status.NONE)
    val rocketsStatus = _rocketsStatus as State<Status>

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

            if(rocketsStatus.value == Status.FAILURE) {
                _rocketsStatus.value = Status.LOADING
                delay(1000)
            } else {
                _rocketsStatus.value = Status.LOADING
            }

            val cacheResult = rocketsRepository.getRocketsFromCache().ifSuccessGetOrNull {
                _allRockets.value = it
                _rocketsStatus.value = Status.SUCCESS
            }

            val apiResult = rocketsRepository.fetchRockets().ifSuccessGetOrNull {
                _allRockets.value = it
                _rocketsStatus.value = Status.SUCCESS
            }

            if(cacheResult.isFailure && apiResult.isFailure) {
                _rocketsStatus.value = Status.FAILURE
            }
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