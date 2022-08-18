package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.janruz.spacexapp.ui.screens.RocketDetailScreen
import com.github.janruz.spacexapp.viewmodels.MainViewModel

fun NavGraphBuilder.rocketDetailComposable(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    composable(
        route = "/rockets/{rocketId}",
        arguments = listOf(
            navArgument("rocketId") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val rocketId = backStackEntry.arguments?.getString("rocketId") ?: ""

        val rockets by mainViewModel.rockets.collectAsState()
        val rocket by remember(rocketId) {
            derivedStateOf {
                rockets.single { it.id == rocketId }
            }
        }

        RocketDetailScreen(rocket, onDismiss = {
            navController.navigateUp()
        })
    }
}