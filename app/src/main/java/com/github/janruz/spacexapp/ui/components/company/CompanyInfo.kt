package com.github.janruz.spacexapp.ui.components.company

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.ui.components.InfoColumn
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.utilities.formatAsCurrency
import com.github.janruz.spacexapp.utilities.formatAsNumber

/**
 * Displays data about the specified company.
 */
@Composable
fun CompanyInfo(
    company: Company
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        modifier = Modifier
            .verticalScroll(rememberScrollState(), enabled = true)
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = company.summary,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )

        val infoColumnsModifier = remember {
            Modifier
                .fillMaxWidth()
                .weight(1f)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            InfoColumn(
                label = stringResource(id = R.string.founder),
                text = company.founder,
                infoColumnsModifier
            )

            InfoColumn(
                label = stringResource(id = R.string.founded),
                text = company.founded.toString(),
                infoColumnsModifier
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            InfoColumn(
                label = stringResource(id = R.string.ceo),
                text = company.ceo,
                infoColumnsModifier
            )

            InfoColumn(
                label = stringResource(id = R.string.coo),
                text = company.coo,
                infoColumnsModifier
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            InfoColumn(
                label = stringResource(id = R.string.employees),
                text = company.employees.formatAsNumber(),
                infoColumnsModifier
            )

            InfoColumn(
                label = stringResource(id = R.string.valuation),
                text = company.valuation.formatAsCurrency(),
                infoColumnsModifier
            )
        }
    }
}

@Preview
@Composable
fun CompanyInfoPreview() {
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

    CompanyInfo(company = sampleCompany)
}