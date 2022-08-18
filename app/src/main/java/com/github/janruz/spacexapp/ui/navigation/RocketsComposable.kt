package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.screens.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun NavGraphBuilder.rocketsComposable(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    composable(
        route = "/rockets"
    ) {
        val rockets by mainViewModel.rockets.collectAsState()

        val localScope = LocalLifecycleOwner.current.lifecycleScope

        RocketsScreen(rockets, onRocketClick = { rocket ->
            localScope.launch {
                withContext(Dispatchers.Default) {
                    delay(200)
                }
                navController.navigate("/rockets/${rocket.id}")
            }

        })
    }
}