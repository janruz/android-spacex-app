package com.github.janruz.spacexapp.ui.screens.rockets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.ErrorIndicator
import com.github.janruz.spacexapp.ui.components.LoadingIndicator
import com.github.janruz.spacexapp.ui.components.rockets.list.RocketsList
import com.github.janruz.spacexapp.ui.components.rockets.list.RocketsListState
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.utilities.Status

@Composable
fun RocketsScreen(
    rocketsStatus: Status,
    rocketsListState: RocketsListState,
    onTryAgain: () -> Unit
) {
    when(rocketsStatus) {
        Status.SUCCESS -> RocketsList(state = rocketsListState, modifier = Modifier.fillMaxSize())
        Status.FAILURE -> {
            ErrorIndicator(
                message = stringResource(id = R.string.error_get_rockets),
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