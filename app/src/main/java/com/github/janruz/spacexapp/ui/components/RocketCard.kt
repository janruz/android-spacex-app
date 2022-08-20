package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.theme.border

@Composable
fun RocketCard(
    rocket: Rocket,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 6.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 6.dp
            )
            .clickable { onClick() },
        elevation = 3.dp,
        border = BorderStroke(width = 1.dp, MaterialTheme.colors.border)
    ) {
        Column {
            AspectRatioImage(imageUrl = rocket.images.firstOrNull())

            RocketActiveLabel(isActive = rocket.active)

            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp)
            )

            Text(
                rocket.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
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
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        )
    )

    RocketCard(rocket = sampleRocket, onClick = {})
}