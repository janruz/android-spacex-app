package com.github.janruz.spacexapp.ui.screens.company

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.ui.components.ErrorIndicator
import com.github.janruz.spacexapp.ui.components.LoadingIndicator
import com.github.janruz.spacexapp.ui.components.company.CompanyInfo
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.utilities.Status

/**
 * A screen showing details about the company
 * @param companyStatus the status of the task of getting the company data
 * @param onTryAgain the function called when there is an error screen showing and the user taps
 * on a "try again" button indicating they want the app to try to get company data again.
 */
@Composable
fun CompanyInfoScreen(
    companyStatus: Status,
    company: Company?,
    onTryAgain: () -> Unit
) {
    when(companyStatus) {
        Status.SUCCESS -> company?.let { CompanyInfo(company) }
        Status.FAILURE -> {
            ErrorIndicator(
                message = stringResource(id = R.string.error_get_company),
                onTryAgain,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium)
            )
        }
        Status.LOADING -> LoadingIndicator()
        Status.NONE -> {}
    }
}

@Preview
@Composable
fun CompanyInfoScreenPreview() {
    val sampleCompany = Company(
        id = "5eb75edc42fea42237d7f3ed",
        founder = "Elon Musk",
        founded = 2002,
        employees = 8000,
        launchSites = 2,
        ceo = "Elon Musk",
        cto = "Elon Musk",
        coo = "Gwynne Shotwell",
        valuation = 90000000,
        summary = "SpaceX designs, manufactures and launches advanced rockets and spacecraft. The company was founded in 2002 to revolutionize space technology, with the ultimate goal of enabling people to live on other planets."
    )

    CompanyInfoScreen(
        companyStatus = Status.SUCCESS,
        company = sampleCompany,
        onTryAgain = {}
    )
}