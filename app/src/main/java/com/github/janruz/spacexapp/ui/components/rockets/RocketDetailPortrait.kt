package com.github.janruz.spacexapp.ui.components.rockets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.AspectRatioImage
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.animation.FadeIn

@Composable
fun RocketDetailPortrait(
    rocket: Rocket,
    onDismiss: () -> Unit
) {
    FadeIn {
        Column {
            Box(contentAlignment = Alignment.TopStart) {
                AspectRatioImage(imageUrl = rocket.images.firstOrNull())

                CloseIcon(onClick = onDismiss)
            }

            RocketActiveLabel(isActive = rocket.active)

            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
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
                    .padding(horizontal = 12.dp)
                    .padding(top = 6.dp)
            )

            Text(
                text = stringResource(id = R.string.first_flight),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 24.dp)
            )

            Text(
                rocket.firstFlight,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )
        }
    }
}