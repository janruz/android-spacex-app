package com.github.janruz.spacexapp.ui.components.rockets.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.animation.FadeIn
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * Presents the details of the given rocket optimized for the portrait orientation.
 */
@Composable
fun RocketDetailPortrait(
    rocket: Rocket,
    onDismiss: () -> Unit
) {
    val spacing = MaterialTheme.spacing.small

    FadeIn {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            item {
                Box(contentAlignment = Alignment.TopStart) {
                    AsyncImage(
                        model = rocket.images.firstOrNull(),
                        contentDescription = stringResource(id = R.string.semantics_rocket_image),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .aspectRatio(ratio = 4 / 3f)
                            .fillMaxWidth()
                    )

                    CloseIcon(onClick = onDismiss)
                }
            }
            rocketInformationItems(
                rocket,
                modifier = Modifier.padding(horizontal = spacing)
            )
        }
    }
}