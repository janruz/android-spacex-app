package com.github.janruz.spacexapp.ui.screens.rockets

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.ErrorIndicator
import com.github.janruz.spacexapp.ui.components.LoadingIndicator
import com.github.janruz.spacexapp.ui.components.rockets.RocketsList
import com.github.janruz.spacexapp.ui.components.rockets.RocketsListState
import com.github.janruz.spacexapp.viewmodels.Status

@Composable
fun RocketsScreen(
    rocketsStatus: Status,
    rocketsListState: RocketsListState,
    onTryAgain: () -> Unit
) {
    when(rocketsStatus) {
        Status.SUCCESS -> RocketsList(state = rocketsListState)
        Status.FAILURE -> {
            ErrorIndicator(message = stringResource(id = R.string.error_get_rockets), onTryAgain)
        }
        Status.LOADING -> LoadingIndicator()
        Status.NONE -> {}
    }
}