package com.github.janruz.spacexapp.ui.components.rockets.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.animation.FadeIn
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * Presents the details of the given rocket optimized for the landscape orientation.
 */
@Composable
fun RocketDetailLandscape(
    rocket: Rocket,
    onDismiss: () -> Unit
) {
    FadeIn {
        Row {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .weight(1f)
            ) {
                AsyncImage(
                    model = rocket.images.firstOrNull(),
                    contentDescription = stringResource(id = R.string.semantics_rocket_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                CloseIcon(onClick = onDismiss)
            }

            LazyColumn(
                contentPadding = PaddingValues(MaterialTheme.spacing.small),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier
                    .weight(1f)
            ) {
                rocketInformationItems(rocket)
            }
        }
    }
}

@Preview
@Composable
fun RocketDetailLandscapePreview() {
    val sampleRocket = Rocket(
        id = "1",
        name = "Falcon 10",
        active = true,
        firstFlight = "2010-06-04",
        description = "Falcon 10 is the next generation of falcons.",
        successRate = 40U,
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        ),
        mass = 8930003293,
        height = 123894f,
        diameter = 12f,
        wikipediaUrl = "https://en.wikipedia.org/wiki/Falcon_Heavy"
    )

    RocketDetailLandscape(rocket = sampleRocket, onDismiss = {})
}