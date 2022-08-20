package com.github.janruz.spacexapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.mockRockets
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.RadioTextButton
import com.github.janruz.spacexapp.ui.components.RocketCard
import com.github.janruz.spacexapp.ui.theme.SpaceXAppTheme
import com.github.janruz.spacexapp.ui.theme.border
import com.github.janruz.spacexapp.viewmodels.RocketActiveFilter
import kotlinx.coroutines.delay

@Composable
fun RocketsScreen(
    rockets: List<Rocket>,
    activeFilter: RocketActiveFilter,
    setActiveFilter: (RocketActiveFilter) -> Unit,
    successRateFilter: Float,
    onSuccessRateFilterChanged: (Float) -> Unit,
    onSuccessRateFilterSelected: () -> Unit,
    onRocketClick: (Rocket) -> Unit
) {
    var rocketCardsVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(rockets) {
        if(!rocketCardsVisible && rockets.isNotEmpty()) {
            delay(500)
            rocketCardsVisible = true
        }
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 12.dp, end = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.rocket_activity),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )

                Row(Modifier.selectableGroup()) {
                    RadioTextButton(
                        text = stringResource(id = R.string.rocket_filter_activity_all),
                        selected = activeFilter == RocketActiveFilter.ALL,
                        onSelect = { setActiveFilter(RocketActiveFilter.ALL) }
                    )

                    RadioTextButton(
                        text = stringResource(id = R.string.rocket_filter_activity_active),
                        selected = activeFilter == RocketActiveFilter.ACTIVE,
                        onSelect = { setActiveFilter(RocketActiveFilter.ACTIVE) }
                    )

                    RadioTextButton(
                        text = stringResource(id = R.string.rocket_filter_activity_inactive),
                        selected = activeFilter == RocketActiveFilter.INACTIVE,
                        onSelect = { setActiveFilter(RocketActiveFilter.INACTIVE) }
                    )
                }

                Text(
                    text = stringResource(id = R.string.rocket_minimum_success_rate) + ": ${successRateFilter.toInt()} %",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Slider(
                    value = successRateFilter,
                    onValueChange = { onSuccessRateFilterChanged(it) },
                    onValueChangeFinished = onSuccessRateFilterSelected,
                    valueRange = 0f..100f,
                    steps = 9,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colors.onBackground,
                        activeTrackColor = MaterialTheme.colors.secondary,
                        inactiveTrackColor = MaterialTheme.colors.border,
                        inactiveTickColor = MaterialTheme.colors.onBackground,
                        activeTickColor = MaterialTheme.colors.onBackground
                    )
                )
            }
        }

        item {
            Spacer(modifier = Modifier.padding(3.dp))
        }

        items(rockets) { rocket ->
            AnimatedVisibility(
                visible = rocketCardsVisible,
                enter = slideInHorizontally(animationSpec = tween(durationMillis = 400), initialOffsetX = {-it}),
                exit = slideOutHorizontally()
            ) {
                RocketCard(rocket, onClick = { onRocketClick(rocket) })
            }
        }

        item {
            Spacer(modifier = Modifier.padding(3.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RocketsScreenPreview() {
    SpaceXAppTheme {
        RocketsScreen(
            rockets = mockRockets,
            activeFilter = RocketActiveFilter.ALL,
            setActiveFilter = {},
            successRateFilter = 0f,
            onSuccessRateFilterChanged = {},
            onSuccessRateFilterSelected = {},
            onRocketClick = {}
        )
    }
}