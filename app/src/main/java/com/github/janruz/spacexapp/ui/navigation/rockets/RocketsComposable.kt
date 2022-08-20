package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        val rockets by rocketsViewModel.rockets.collectAsState()
        val (activeFilter, setActiveFilter) = rocketsViewModel.activeFilterEnabled

        val scope = rememberCoroutineScope()

        RocketsScreen(
            rockets,
            activeFilter,
            setActiveFilter,
            onRocketClick = { rocket ->
                scope.launch {
                    delay(200)
                    navigateToDetailScreen(rocket.id)
                }
            }
        )
    }
}