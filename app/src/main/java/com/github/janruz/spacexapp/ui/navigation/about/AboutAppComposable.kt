package com.github.janruz.spacexapp.ui.navigation.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.about.AboutAppScreen

fun NavGraphBuilder.aboutAppComposable() {
    composable(
        route = NavConstants.ABOUT_APP_SCREEN
    ) {
        AboutAppScreen()
    }
}