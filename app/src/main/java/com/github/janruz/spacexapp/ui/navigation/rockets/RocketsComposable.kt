package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.components.rockets.rememberRocketsListState
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.rockets.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel

fun NavGraphBuilder.rocketsComposable(
    navController: NavHostController,
    navigateToDetailScreen: (rocketId: String) -> Unit
) {
    composable(
        route = NavConstants.ROCKETS_LIST_SCREEN
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(NavConstants.ROCKETS_GRAPH)
        }

        val rocketsViewModel = hiltViewModel<RocketsViewModel>(parentEntry)

        val rocketsStatus by rocketsViewModel.rocketsStatus

        RocketsScreen(
            rocketsStatus = rocketsStatus,
            rocketsListState = rememberRocketsListState(rocketsViewModel, navigateToDetailScreen),
            onTryAgain = {
                rocketsViewModel.getRockets()
            }
        )
    }
}
