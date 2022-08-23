package com.github.janruz.spacexapp.ui.screens.rockets

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.AspectRatioImage
import com.github.janruz.spacexapp.ui.components.rockets.RocketActiveLabel
import kotlinx.coroutines.delay

@Composable
fun RocketDetailScreen(
    rocket: Rocket,
    onDismiss: () -> Unit
) {
    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(350)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it }
        )+ expandVertically(
            expandFrom = Alignment.Bottom
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {

        Column {
            Box(contentAlignment = Alignment.TopStart) {
                AspectRatioImage(imageUrl = rocket.images.firstOrNull())

                IconButton(
                    onClick = onDismiss
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(alpha = 0.5f, red = 0f, green = 0f, blue = 0f))
                            .padding(4.dp)
                    )
                }
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

@Preview
@Composable
private fun RocketDetailScreenPreview() {
    val sampleRocket = Rocket(
        id = "1",
        name = "Falcon 10",
        active = true,
        firstFlight = "2010-06-04",
        description = "Falcon 10 is the next generation of falcons.",
        successRate = 40U,
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        )
    )

    RocketDetailScreen(rocket = sampleRocket, onDismiss = {})
}