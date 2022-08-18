package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.screens.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.MainViewModel

fun NavGraphBuilder.rocketsComposable(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    composable(
        route = "/rockets"
    ) {
        val rockets by mainViewModel.rockets.collectAsState()
        RocketsScreen(rockets, onRocketClick = { rocket ->
            navController.navigate("/rockets/${rocket.id}")
        })
    }
}