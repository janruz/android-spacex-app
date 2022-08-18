package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.data.models.Rocket

@Composable
fun AspectRatioImage(
    imageUrl: String?,
    aspectRatio: Float = 4 / 3f
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .aspectRatio(ratio = aspectRatio)
            .fillMaxWidth()
    )
}