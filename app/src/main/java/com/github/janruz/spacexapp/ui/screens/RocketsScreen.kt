package com.github.janruz.spacexapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.data.mockRockets
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.RocketCard
import com.github.janruz.spacexapp.ui.theme.SpaceXAppTheme
import kotlinx.coroutines.delay

@Composable
fun RocketsScreen(
    rockets: List<Rocket>,
    onRocketClick: (Rocket) -> Unit
) {
    var rocketCardsVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(rockets) {
        if(!rocketCardsVisible && rockets.isNotEmpty()) {
            delay(500)
            rocketCardsVisible = true
        }
    }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(3.dp))
        }

        items(rockets) { rocket ->
            AnimatedVisibility(
                visible = rocketCardsVisible,
                enter = slideInHorizontally(animationSpec = tween(durationMillis = 400), initialOffsetX = {-it}),
                exit = slideOutHorizontally()
            ) {
                RocketCard(rocket, onClick = { onRocketClick(rocket) })
            }
        }

        item {
            Spacer(modifier = Modifier.padding(3.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RocketsScreenPreview() {
    SpaceXAppTheme {
        RocketsScreen(rockets = mockRockets, onRocketClick = {})
    }
}