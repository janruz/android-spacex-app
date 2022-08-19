package com.github.janruz.spacexapp.ui.navigation.company

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.CompanyInfoScreen

fun NavGraphBuilder.companyInfoComposable() {
    composable(
        route = NavConstants.COMPANY_INFO_SCREEN
    ) {
        CompanyInfoScreen()
    }
}