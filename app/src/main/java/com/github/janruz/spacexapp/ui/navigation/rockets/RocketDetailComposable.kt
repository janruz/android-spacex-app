package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.RocketDetailScreen
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel

fun NavGraphBuilder.rocketDetailComposable(
    navController: NavHostController,
    navigateUp: () -> Unit
) {
    composable(
        route = NavConstants.ROCKET_DETAIL_SCREEN,
        arguments = listOf(
            navArgument(NavConstants.ROCKET_ID_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(NavConstants.ROCKETS_GRAPH)
        }

        val rocketsViewModel = hiltViewModel<RocketsViewModel>(parentEntry)

        val rocketId = backStackEntry.arguments?.getString(NavConstants.ROCKET_ID_KEY) ?: ""

        val rocket by remember(rocketId) {
            derivedStateOf {
                rocketsViewModel.allRockets.value.single { it.id == rocketId }
            }
        }

        RocketDetailScreen(rocket, onDismiss = navigateUp)
    }
}