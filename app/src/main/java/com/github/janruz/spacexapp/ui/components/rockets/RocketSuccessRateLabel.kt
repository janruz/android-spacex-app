package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.IconLabel
import com.github.janruz.spacexapp.ui.theme.negative
import com.github.janruz.spacexapp.ui.theme.neutral
import com.github.janruz.spacexapp.ui.theme.positive
import com.github.janruz.spacexapp.utilities.formatAsPercent

@Composable
fun RocketSuccessRateLabel(
    successRate: UInt,
    modifier: Modifier = Modifier
) {
    IconLabel(
        text = successRate.toFloat().formatAsPercent(alreadyInPercent = true),
        iconPainter = painterResource(id = R.drawable.ic_rocket_launch),
        color = when(successRate) {
            in 99U..100U -> MaterialTheme.colors.positive
            in 50U..98U -> MaterialTheme.colors.neutral
            else -> MaterialTheme.colors.negative
        },
        modifier = modifier
    )
}