package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.animation.FadeIn
import kotlinx.coroutines.delay

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
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                CloseIcon(onClick = onDismiss)
            }

            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    RocketActiveLabel(isActive = rocket.active)
                }
                item {
                    Text(
                        rocket.name,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                item {
                    Text(
                        rocket.description,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.first_flight),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground,
                    )

                    Text(
                        rocket.firstFlight,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
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
        )
    )

    RocketDetailLandscape(rocket = sampleRocket, onDismiss = {})
}