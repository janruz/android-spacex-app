package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.models.Rocket
import com.github.janruz.spacexapp.ui.theme.border

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
            )

            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
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