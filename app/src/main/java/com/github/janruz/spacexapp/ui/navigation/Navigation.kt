package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.github.janruz.spacexapp.ui.navigation.about.aboutAppComposable
import com.github.janruz.spacexapp.ui.navigation.company.companyInfoComposable
import com.github.janruz.spacexapp.ui.navigation.rockets.rocketDetailComposable
import com.github.janruz.spacexapp.ui.navigation.rockets.rocketsComposable

@Composable
fun SetupNavigation(
    navController: NavHostController,
    navigator: Navigator,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavConstants.COMPANY_INFO_SCREEN,
        modifier = Modifier.padding(paddingValues)
    ) {

        companyInfoComposable()

        navigation(
            startDestination = NavConstants.ROCKETS_LIST_SCREEN,
            route = NavConstants.ROCKETS_GRAPH
        ) {
            rocketsComposable(
                navController,
                navigateToDetailScreen = navigator.toRocketDetail
            )

            rocketDetailComposable(
                navController,
                navigateUp = navigator.up
            )
        }

        aboutAppComposable()
    }
}


