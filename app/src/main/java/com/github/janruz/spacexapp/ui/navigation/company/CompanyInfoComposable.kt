package com.github.janruz.spacexapp.ui.navigation.company

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.CompanyInfoScreen
import com.github.janruz.spacexapp.viewmodels.CompanyViewModel
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel

fun NavGraphBuilder.companyInfoComposable() {
    composable(
        route = NavConstants.COMPANY_INFO_SCREEN
    ) {
        val companyViewModel = hiltViewModel<CompanyViewModel>()
        val company by companyViewModel.company.collectAsState()

        company?.let { safeCompany ->
            CompanyInfoScreen(safeCompany)
        }
    }
}