package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun NavGraphBuilder.rocketsComposable(
    navigateToDetailScreen: (rocketId: String) -> Unit,
    mainViewModel: MainViewModel
) {
    composable(
        route = NavConstants.ROCKETS_LIST_SCREEN
    ) {
        val rockets by mainViewModel.rockets.collectAsState()

        val localScope = LocalLifecycleOwner.current.lifecycleScope

        RocketsScreen(rockets, onRocketClick = { rocket ->
            localScope.launch {
                withContext(Dispatchers.Default) {
                    delay(200)
                }
                navigateToDetailScreen(rocket.id)
            }

        })
    }
}