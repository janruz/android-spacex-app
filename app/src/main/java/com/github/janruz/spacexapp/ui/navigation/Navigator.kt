package com.github.janruz.spacexapp.ui.navigation

import androidx.navigation.NavHostController
import com.github.janruz.spacexapp.ui.components.NavDrawerItem
import com.github.janruz.spacexapp.ui.navigation.NavConstants.ROCKET_DETAIL_SCREEN_PREFIX

/**
 * The Navigator class is responsible for encapsulating navigation logic
 * meaning all the interaction with the NavHostController.
 * Its main benefits are that all
 * navigation logic is in one place and that we do not have to pass an
 * instance of the NavHostController to every composable that needs to trigger
 * a navigation action. Instead we pass only a reference to the corresponding Navigator
 * method which offers clear interface describing exactly what parameters are necessary
 * for the given navigation action.
 */
class Navigator(
    navController: NavHostController
) {
    val toRocketDetail: (rocketId: String) -> Unit = { rocketId ->
        navController.navigate("$ROCKET_DETAIL_SCREEN_PREFIX/${rocketId}")
    }

    val up: () -> Unit = {
        navController.navigateUp()
    }

    val toDrawerItem: (NavDrawerItem) -> Unit = { item ->
        navController.navigate(item.defaultRoute) {
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId)
        }
    }
}