package com.github.janruz.spacexapp.ui.navigation.rockets

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.components.rockets.list.rememberRocketsListState
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.rockets.RocketsScreen
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel

/**
 * The rockets list destination
 */
fun NavGraphBuilder.rocketsComposable(
    navController: NavHostController,
    navigateToDetailScreen: (rocketId: String) -> Unit
) {
    composable(
        route = NavConstants.ROCKETS_LIST_SCREEN
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(NavConstants.ROCKETS_SECTION)
        }

        val rocketsViewModel = hiltViewModel<RocketsViewModel>(parentEntry)

        val context = LocalContext.current
        LaunchedEffect(Unit) {
            rocketsViewModel.messageId.collect { messageId ->
                Toast.makeText(context, context.getString(messageId), Toast.LENGTH_SHORT).show()
            }
        }

        RocketsScreen(
            rocketsStatus = rocketsViewModel.rocketsStatus.value,
            rocketsListState = rememberRocketsListState(rocketsViewModel, navigateToDetailScreen),
            onTryAgain = {
                rocketsViewModel.getRockets()
            }
        )
    }
}
