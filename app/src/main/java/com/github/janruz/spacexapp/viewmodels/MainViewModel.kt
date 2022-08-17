package com.github.janruz.spacexapp.viewmodels

import androidx.lifecycle.ViewModel
import com.github.janruz.spacexapp.data.mockRockets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _rockets = MutableStateFlow(mockRockets)
    val rockets = _rockets.asStateFlow()
}