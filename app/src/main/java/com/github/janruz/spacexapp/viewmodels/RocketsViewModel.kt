package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.repositories.RocketsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    rocketsRepository: RocketsRepository
): ViewModel() {

    val rockets = derivedStateOf {
        _allRockets.value
            .applyFiltering(
                activeFilter = activeFilter.value,
                minimumSuccessRate = successRateFilter.value
            )
    }
    val activeFilter = mutableStateOf(RocketActiveFilter.ALL)
    val successRateFilter = mutableStateOf(0U)

    private var _allRockets = mutableStateOf(listOf<Rocket>())
    val allRockets = _allRockets as State<List<Rocket>>

    init {
        viewModelScope.launch {
            val savedRocketsResult = rocketsRepository.getRocketsFromCache()
            if(savedRocketsResult.isSuccess) {
                savedRocketsResult.getOrNull()?.let {
                    _allRockets.value = it
                }
            }

            val updatedRocketsResult = rocketsRepository.fetchRockets()
            if(updatedRocketsResult.isSuccess) {
                updatedRocketsResult.getOrNull()?.let {
                    _allRockets.value = it
                }
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