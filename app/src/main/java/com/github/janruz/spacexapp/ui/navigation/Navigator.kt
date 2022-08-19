package com.github.janruz.spacexapp.ui.navigation

import androidx.navigation.NavHostController
import com.github.janruz.spacexapp.ui.navigation.NavConstants.ROCKET_DETAIL_SCREEN_PREFIX

class Navigator(
    navController: NavHostController
) {
    val toRocketDetail: (rocketId: String) -> Unit = { rocketId ->
        navController.navigate("$ROCKET_DETAIL_SCREEN_PREFIX/${rocketId}")
    }

    val up: () -> Unit = {
        navController.navigateUp()
    }
}