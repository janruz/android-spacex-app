package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.*
import com.github.janruz.spacexapp.ui.theme.border
import kotlin.math.roundToInt

@Composable
fun RocketSuccessRateSlider(
    successRateFilter: Float,
    setSuccessRateFilter: (Float) -> Unit,
    onSuccessRateFilterSelected: (UInt) -> Unit
) {
    var currentValue by remember(successRateFilter) {
        mutableStateOf(successRateFilter)
    }

    Slider(
        value = currentValue,
        onValueChange = {
            currentValue = it
            setSuccessRateFilter(it)
        },
        onValueChangeFinished = {
            onSuccessRateFilterSelected(
                ((currentValue / 10f).roundToInt() * 10).toUInt()
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