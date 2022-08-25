package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.janruz.spacexapp.ui.theme.border
import kotlin.math.roundToInt

/**
 * Displays a slider for picking the minimum rocket success rate
 */
@Composable
fun RocketSuccessRateSlider(
    successRateFilter: Float,
    setSuccessRateFilter: (Float) -> Unit,
    onSuccessRateFilterSelected: (UInt) -> Unit,
    modifier: Modifier = Modifier
) {
    // The purpose of this state mirroring the hoisted successRateFilter state is just
    // to encapsulate the current value of the slider so that it is available in
    // the onValueChangeFinished lambda below. If we used successRateFilter in the lambdas,
    // we would in fact refer to the capture of the value of the successRateFilter
    // from the latest recomposition, not necessarily the current value of the successRateFilter
    // as the layout may not have recomposed by the time the onValueChangeFinished is called.
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
        ),
        modifier = modifier
    )
}