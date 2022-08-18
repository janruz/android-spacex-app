package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.theme.activeGreen
import com.github.janruz.spacexapp.ui.theme.border
import com.github.janruz.spacexapp.ui.theme.red

@Composable
fun RocketCard(
    rocket: Rocket
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 6.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 6.dp
            ),
        elevation = 3.dp,
        border = BorderStroke(width = 1.dp, MaterialTheme.colors.border)
    ) {
        Column {
            AsyncImage(
                model = rocket.images.firstOrNull(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(ratio = 4 / 3f)
                    .fillMaxWidth()
                    .layoutId("rocket_image")
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp)
                    .layoutId("active_label")
            ) {
                Text(
                    text = if(rocket.active) "Active" else "Inactive",
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if(rocket.active) MaterialTheme.colors.activeGreen else MaterialTheme.colors.red)
                        .padding(vertical = 4.dp, horizontal = 6.dp)
                )
            }

            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp)
            )

            Text(
                rocket.description,
                style = MaterialTheme.typography.body1,
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

    RocketCard(rocket = sampleRocket)
}