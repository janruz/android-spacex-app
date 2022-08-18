package com.github.janruz.spacexapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.janruz.spacexapp.ui.screens.RocketDetailScreen

fun NavGraphBuilder.rocketDetailComposable() {
    composable(
        route = "/rockets/{rocketId}",
        arguments = listOf(
            navArgument("rocketId") {
                type = NavType.StringType
            }
        )
    ) {
        RocketDetailScreen()
    }
}