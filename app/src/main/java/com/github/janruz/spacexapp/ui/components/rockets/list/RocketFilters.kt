package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.RadioTextButton
import com.github.janruz.spacexapp.utilities.formatAsPercent
import com.github.janruz.spacexapp.viewmodels.RocketActiveFilter

@Composable
fun RocketFilters(
    state: RocketFiltersState,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(id = R.string.rocket_activity),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )

        Row(Modifier.selectableGroup()) {
            RadioTextButton(
                text = stringResource(id = R.string.rocket_filter_activity_all),
                selected = state.activeFilter == RocketActiveFilter.ALL,
                onSelect = { state.activeFilter = RocketActiveFilter.ALL }
            )

            RadioTextButton(
                text = stringResource(id = R.string.rocket_filter_activity_active),
                selected = state.activeFilter == RocketActiveFilter.ACTIVE,
                onSelect = { state.activeFilter = RocketActiveFilter.ACTIVE }
            )

            RadioTextButton(
                text = stringResource(id = R.string.rocket_filter_activity_inactive),
                selected = state.activeFilter == RocketActiveFilter.INACTIVE,
                onSelect = { state.activeFilter = RocketActiveFilter.INACTIVE }
            )
        }

        Text(
            text = stringResource(
                id = R.string.rocket_minimum_success_rate,
                state.successRateFilterRealtime.formatAsPercent(alreadyInPercent = true)
            ),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )

        RocketSuccessRateSlider(
            state.successRateFilterRealtime,
            setSuccessRateFilter = { state.successRateFilterRealtime = it },
            onSuccessRateFilterSelected = { state.onSuccessRateFilterSelected(it) }
        )
    }
}

@Composable
fun rememberRocketFiltersState(
    listState: RocketsListState
): RocketFiltersState = remember(listState) {
    RocketFiltersState(listState)
}

class RocketFiltersState(
    listState: RocketsListState
) {
    var activeFilter by listState.activeFilter
    var successRateFilterRealtime by listState.successRateFilterRealtime
    val onSuccessRateFilterSelected = listState::onSuccessRateFilterSelected
}