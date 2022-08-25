package com.github.janruz.spacexapp.ui.components.rockets.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.InfoColumn
import com.github.janruz.spacexapp.ui.components.rockets.RocketActiveLabel
import com.github.janruz.spacexapp.ui.components.rockets.RocketSuccessRateLabel
import com.github.janruz.spacexapp.ui.theme.negative
import com.github.janruz.spacexapp.ui.theme.positive
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.utilities.formatAsDate
import com.github.janruz.spacexapp.utilities.formatAsNumber
import com.github.janruz.spacexapp.utilities.openWikipedia

/**
 * Defines a set of lazy list items for displaying detailed information about the given rocket.
 */
fun LazyListScope.rocketInformationItems(
    rocket: Rocket,
    modifier: Modifier = Modifier
) {
    item {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = modifier
        ) {
            RocketActiveLabel(
                isActive = rocket.active
            )

            RocketSuccessRateLabel(
                successRate = rocket.successRate
            )
        }
    }

    item {
        Text(
            rocket.name,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,
            modifier = modifier
        )
    }

    item {
        Text(
            rocket.description,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = modifier
        )
    }

    item {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(modifier) {

            InfoColumn(
                label = stringResource(id = R.string.first_flight),
                text = rocket.firstFlight.formatAsDate(context = LocalContext.current),
                modifier = Modifier.weight(1f)
            )

            InfoColumn(
                label = stringResource(id = R.string.mass),
                text = stringResource(id = R.string.mass_kg_format, rocket.mass.formatAsNumber()),
                modifier = Modifier.weight(1f)
            )
        }
    }

    item {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(modifier) {

            InfoColumn(
                label = stringResource(id = R.string.height),
                text = stringResource(id = R.string.dimension_meters_format, rocket.height.formatAsNumber()),
                modifier = Modifier.weight(1f)
            )

            InfoColumn(
                label = stringResource(id = R.string.diameter),
                text = stringResource(id = R.string.dimension_meters_format, rocket.diameter.formatAsNumber()),
                modifier = Modifier.weight(1f)
            )
        }
    }

    item {
        Divider(
            modifier
                .padding(vertical = MaterialTheme.spacing.small)
        )
    }

    item {
        val context = LocalContext.current

        Button(
            onClick = {
                rocket.openWikipedia(context)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(rocket.active) MaterialTheme.colors.positive else MaterialTheme.colors.negative,
                contentColor = MaterialTheme.colors.background
            ),
            modifier = modifier
        ) {
            Text(text = stringResource(id = R.string.wikipedia))
        }
    }
}