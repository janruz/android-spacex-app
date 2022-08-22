package com.github.janruz.spacexapp.ui.navigation.company

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.screens.company.CompanyInfoScreen
import com.github.janruz.spacexapp.viewmodels.CompanyViewModel

fun NavGraphBuilder.companyInfoComposable() {
    composable(
        route = NavConstants.COMPANY_INFO_SCREEN
    ) {
        val companyViewModel = hiltViewModel<CompanyViewModel>()

        val context = LocalContext.current
        LaunchedEffect(Unit) {
            companyViewModel.messageId.collect { messageId ->
                Toast.makeText(context, context.getString(messageId), Toast.LENGTH_SHORT).show()
            }
        }

        CompanyInfoScreen(
            companyStatus = companyViewModel.companyStatus.value,
            company = companyViewModel.company.value,
            onTryAgain = {
                companyViewModel.getCompany()
            }
        )
    }
}