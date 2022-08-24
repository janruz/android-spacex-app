package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.AspectRatioImage
import com.github.janruz.spacexapp.ui.components.rockets.RocketActiveLabel
import com.github.janruz.spacexapp.ui.components.rockets.RocketSuccessRateLabel
import com.github.janruz.spacexapp.ui.theme.border

@Composable
fun RocketCard(
    rocket: Rocket,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 3.dp,
        border = BorderStroke(width = 1.dp, MaterialTheme.colors.border)
    ) {
        Column {
            AspectRatioImage(imageUrl = rocket.images.firstOrNull())

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp)
            ) {
                RocketActiveLabel(
                    isActive = rocket.active
                )

                RocketSuccessRateLabel(
                    successRate = rocket.successRate
                )
            }

            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp)
            )

            Text(
                rocket.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RocketCardPreview() {
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

    RocketCard(rocket = sampleRocket, onClick = {})
}