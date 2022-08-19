package com.github.janruz.spacexapp.ui.navigation

import androidx.navigation.NavHostController

class Navigator(
    navController: NavHostController
) {
    val toRocketDetail: (rocketId: String) -> Unit = { rocketId ->
        navController.navigate("/rockets/${rocketId}")
    }

    val up: () -> Unit = {
        navController.navigateUp()
    }
}