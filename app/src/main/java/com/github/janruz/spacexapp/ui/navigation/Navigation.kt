package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.janruz.spacexapp.ui.navigation.company.companyInfoComposable
import com.github.janruz.spacexapp.ui.navigation.rockets.rocketDetailComposable
import com.github.janruz.spacexapp.ui.navigation.rockets.rocketsComposable
import com.github.janruz.spacexapp.viewmodels.MainViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val navigator = remember(navController) {
        Navigator(navController)
    }

    NavHost(
        navController = navController,
        startDestination = NavConstants.COMPANY_INFO_SCREEN,
        modifier = Modifier.padding(paddingValues)
    ) {

        companyInfoComposable()
        rocketsComposable(navigateToDetailScreen = navigator.toRocketDetail, mainViewModel)
        rocketDetailComposable(navigateUp = navigator.up, mainViewModel)
    }
}


