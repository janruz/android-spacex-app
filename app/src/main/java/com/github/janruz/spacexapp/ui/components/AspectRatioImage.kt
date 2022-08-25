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
    contentDescription: String?,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 4 / 3f
) {
    AsyncImage(
        model = imageUrl,
        contentDescription,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .aspectRatio(ratio = aspectRatio)
            .fillMaxWidth()
    )
}