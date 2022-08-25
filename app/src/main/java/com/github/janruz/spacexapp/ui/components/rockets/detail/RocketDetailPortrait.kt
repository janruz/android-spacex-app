package com.github.janruz.spacexapp.ui.components.rockets.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.AspectRatioImage
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.animation.FadeIn
import com.github.janruz.spacexapp.ui.theme.spacing

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
                    AspectRatioImage(
                        imageUrl = rocket.images.firstOrNull(),
                        contentDescription = stringResource(id = R.string.semantics_rocket_image)
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