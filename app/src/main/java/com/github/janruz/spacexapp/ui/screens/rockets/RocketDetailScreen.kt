package com.github.janruz.spacexapp.ui.screens.rockets

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.ScreenOrientation
import com.github.janruz.spacexapp.ui.components.rememberScreenOrientation
import com.github.janruz.spacexapp.ui.components.rockets.RocketDetailLandscape
import com.github.janruz.spacexapp.ui.components.rockets.RocketDetailPortrait

@Composable
fun RocketDetailScreen(
    rocket: Rocket,
    onDismiss: () -> Unit
) {
    when(rememberScreenOrientation()) {
        ScreenOrientation.PORTRAIT -> RocketDetailPortrait(rocket, onDismiss)
        ScreenOrientation.LANDSCAPE -> RocketDetailLandscape(rocket, onDismiss)
    }
}

@Preview
@Composable
fun RocketDetailScreenPreview() {
    val sampleRocket = Rocket(
        id = "1",
        name = "Falcon 10",
        active = true,
        firstFlight = "2010-06-04",
        description = "Falcon 10 is the next generation of falcons.",
        successRate = 40U,
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        )
    )

    RocketDetailScreen(rocket = sampleRocket, onDismiss = {})
}