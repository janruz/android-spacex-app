package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.NoData
import com.github.janruz.spacexapp.ui.components.RadioTextButton
import com.github.janruz.spacexapp.viewmodels.RocketActiveFilter
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RocketsList(
    state: RocketsListState
) {
    var rocketCardsVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.rockets) {
        if(!rocketCardsVisible && state.rockets.isNotEmpty()) {
            delay(500)
            rocketCardsVisible = true
        }
    }

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp),
        columns = GridCells.Adaptive(minSize = 260.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Column {
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
                    text = stringResource(id = R.string.rocket_minimum_success_rate) + ": ${state.successRateFilterRealtime.toInt()} %",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )

                RocketSuccessRateSlider(
                    state.successRateFilterRealtime,
                    onSuccessRateFilterChanged = { state.successRateFilterRealtime = it },
                    onSuccessRateFilterSelected = { state.onSuccessRateFilterSelected(it) }
                )
            }
        }

        when {
            state.isAllRocketsEmpty -> {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    NoData(message = stringResource(id = R.string.no_rockets))
                }
            }
            state.rockets.isEmpty() -> {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    NoData(message = stringResource(id = R.string.no_rockets_matching_given_filters))
                }
            }
            state.rockets.isNotEmpty() -> {

                items(state.rockets) { rocket ->
                    AnimatedVisibility(
                        visible = rocketCardsVisible,
                        enter = slideInHorizontally(animationSpec = tween(durationMillis = 400), initialOffsetX = {-it}),
                        exit = slideOutHorizontally()
                    ) {
                        RocketCard(rocket, onClick = { state.onRocketClick(rocket) })
                    }
                }
            }
        }
    }
}

@Composable
fun rememberRocketsListState(
    rocketsViewModel: RocketsViewModel,
    navigateToDetailScreen: (rocketId: String) -> Unit,
    scope: CoroutineScope = rememberCoroutineScope()

) = remember(rocketsViewModel, scope, navigateToDetailScreen) {
    RocketsListState(rocketsViewModel, scope, navigateToDetailScreen)
}

class RocketsListState(
    private val viewModel: RocketsViewModel,
    private val scope: CoroutineScope,
    private val navigateToDetailScreen: (rocketId: String) -> Unit
) {
    val rockets by viewModel.rockets
    val isAllRocketsEmpty by derivedStateOf {
        viewModel.allRockets.value.isEmpty()
    }

    var activeFilter by viewModel.activeFilter

    var successRateFilterRealtime by mutableStateOf(viewModel.successRateFilter.value.toFloat())

    fun onSuccessRateFilterSelected(value: UInt) {
        viewModel.successRateFilter.value = value
    }

    fun onRocketClick(rocket: Rocket) {
        scope.launch {
            delay(200)
            navigateToDetailScreen(rocket.id)
        }
    }
}