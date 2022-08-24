package com.github.janruz.spacexapp.ui.components.rockets.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.AspectRatioImage
import com.github.janruz.spacexapp.ui.components.CloseIcon
import com.github.janruz.spacexapp.ui.components.InfoColumn
import com.github.janruz.spacexapp.ui.components.animation.FadeIn
import com.github.janruz.spacexapp.ui.components.rockets.RocketActiveLabel
import com.github.janruz.spacexapp.ui.components.rockets.RocketSuccessRateLabel
import com.github.janruz.spacexapp.ui.theme.negative
import com.github.janruz.spacexapp.ui.theme.positive
import com.github.janruz.spacexapp.utilities.formatAsDate

@Composable
fun RocketDetailPortrait(
    rocket: Rocket,
    onDismiss: () -> Unit
) {

    FadeIn {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Box(contentAlignment = Alignment.TopStart) {
                    AspectRatioImage(imageUrl = rocket.images.firstOrNull())

                    CloseIcon(onClick = onDismiss)
                }
            }

            rocketInformationItems(
                rocket,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}