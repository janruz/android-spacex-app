package com.github.janruz.spacexapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.repositories.CompanyRepository
import com.github.janruz.spacexapp.data.repositories.RocketsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    rocketsRepository: RocketsRepository
): ViewModel() {

    private val _rockets = MutableStateFlow(listOf<Rocket>())
    val rockets = _rockets.asStateFlow()

    val activeFilterEnabled = mutableStateOf(RocketActiveFilter.ALL)

    private var allRockets = listOf<Rocket>()

    init {
        viewModelScope.launch {
            val savedRocketsResult = rocketsRepository.getRocketsFromCache()
            if(savedRocketsResult.isSuccess) {
                savedRocketsResult.getOrNull()?.let {
                    _rockets.emit(it)
                }
            }

            val updatedRocketsResult = rocketsRepository.fetchRockets()
            if(updatedRocketsResult.isSuccess) {
                updatedRocketsResult.getOrNull()?.let {
                    _rockets.emit(it)
                }
            }
        }
    }

    enum class RocketActiveFilter {
        ACTIVE, INACTIVE, ALL
    }
}