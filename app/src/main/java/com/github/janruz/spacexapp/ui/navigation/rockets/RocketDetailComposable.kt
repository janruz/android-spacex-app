package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.RocketDetailScreen
import com.github.janruz.spacexapp.viewmodels.MainViewModel

fun NavGraphBuilder.rocketDetailComposable(
    navigateUp: () -> Unit,
    mainViewModel: MainViewModel
) {
    composable(
        route = NavConstants.ROCKET_DETAIL_SCREEN,
        arguments = listOf(
            navArgument(NavConstants.ROCKET_ID_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val rocketId = backStackEntry.arguments?.getString(NavConstants.ROCKET_ID_KEY) ?: ""

        val rockets by mainViewModel.rockets.collectAsState()
        val rocket by remember(rocketId) {
            derivedStateOf {
                rockets.single { it.id == rocketId }
            }
        }

        RocketDetailScreen(rocket, onDismiss = navigateUp)
    }
}