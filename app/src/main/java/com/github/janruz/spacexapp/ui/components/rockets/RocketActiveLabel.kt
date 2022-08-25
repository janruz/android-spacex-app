package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.IconLabel
import com.github.janruz.spacexapp.ui.theme.positive
import com.github.janruz.spacexapp.ui.theme.negative

/**
 * Displays if the rocket is active or not using corresponding label and icon
 */
@Composable
fun RocketActiveLabel(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {

    IconLabel(
        text = stringResource(id = if(isActive) R.string.rocket_active else R.string.rocket_inactive),
        iconPainter = painterResource(id = if(isActive) R.drawable.ic_check else R.drawable.ic_clear),
        color = if (isActive) MaterialTheme.colors.positive else MaterialTheme.colors.negative,
        modifier = modifier
    )
}