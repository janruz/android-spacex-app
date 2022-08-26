package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.NoData
import com.github.janruz.spacexapp.ui.components.maxLineSpanItem
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Displays the rockets in vertical grid
 * @param state the state holder for this composable. Use rememberRocketsListState to get
 * and remember the instance of it.
 */
@Composable
fun RocketsList(
    state: RocketsListState,
    modifier: Modifier = Modifier
) {
    var rocketCardsVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.rockets) {
        if(!rocketCardsVisible && state.rockets.isNotEmpty()) {
            delay(100)
            rocketCardsVisible = true
        }
    }

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        columns = GridCells.Adaptive(minSize = 260.dp),
        modifier = modifier
    ) {
        maxLineSpanItem {
            RocketFilters(state = rememberRocketFiltersState(state))
        }

        when {
            state.isAllRocketsEmpty -> {
                maxLineSpanItem {
                    NoData(message = stringResource(id = R.string.no_rockets))
                }
            }
            state.rockets.isEmpty() -> {
                maxLineSpanItem {
                    NoData(message = stringResource(id = R.string.no_rockets_matching_given_filters))
                }
            }
            state.rockets.isNotEmpty() -> {

                items(state.rockets) { rocket ->
                    RocketCard(rocket, onClick = { state.onRocketClick(rocket) })
                }
            }
        }
    }
}

/**
 * Constructs and remembers an instance of RocketsListState, the state holder
 * for the RocketsList composable.
 */
@Composable
fun rememberRocketsListState(
    rocketsViewModel: RocketsViewModel,
    navigateToDetailScreen: (rocketId: String) -> Unit,
    scope: CoroutineScope = rememberCoroutineScope()

) = remember(rocketsViewModel, scope, navigateToDetailScreen) {
    RocketsListState(rocketsViewModel, scope, navigateToDetailScreen)
}

/**
 * The state holder for the RocketsList composable.
 */
class RocketsListState(
    private val viewModel: RocketsViewModel,
    private val scope: CoroutineScope,
    private val navigateToDetailScreen: (rocketId: String) -> Unit
) {
    val rockets by viewModel.rockets
    val isAllRocketsEmpty by derivedStateOf {
        viewModel.allRockets.value.isEmpty()
    }

    val activeFilter = viewModel.activeFilter
    val successRateFilterRealtime = mutableStateOf(viewModel.successRateFilter.value.toFloat())

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