package com.github.janruz.spacexapp.ui.navigation.rockets

import androidx.compose.runtime.mutableStateOf
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

        val (activeFilter, setActiveFilter) = rocketsViewModel.activeFilter
        val (successRateFilter, setSuccessRateFilter) = rocketsViewModel.successRateFilter

        val (successRateFilterRealtime, setSuccessRateFilterRealtime) = remember { mutableStateOf(successRateFilter.toFloat()) }
        val scope = rememberCoroutineScope()

        RocketsScreen(
            rockets = rocketsViewModel.rockets.value,
            activeFilter,
            setActiveFilter,
            successRateFilterRealtime,
            setSuccessRateFilterRealtime,
            onSuccessRateFilterSelected = { selectedValue ->
                setSuccessRateFilter(selectedValue)
            },
            onRocketClick = { rocket ->
                scope.launch {
                    delay(200)
                    navigateToDetailScreen(rocket.id)
                }
            }
        )
    }
}