package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import com.github.janruz.spacexapp.ui.theme.border
import kotlin.math.roundToInt

@Composable
fun RocketSuccessRateSlider(
    successRateFilter: Float,
    onSuccessRateFilterChanged: (Float) -> Unit,
    onSuccessRateFilterSelected: (UInt) -> Unit
) {
    Slider(
        value = successRateFilter,
        onValueChange = { onSuccessRateFilterChanged(it) },
        onValueChangeFinished = {
            onSuccessRateFilterSelected(
                ((successRateFilter / 10f).roundToInt() * 10).toUInt()
            )
        },
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