package com.github.janruz.spacexapp.ui.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

/**
 * Wraps logic handling fade in animation.
 */
@Composable
fun FadeIn(
    modifier: Modifier = Modifier,
    initialDelay: Long = 300,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    var visible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(initialDelay)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(initialAlpha = 0f),
        modifier = modifier,
        content = content
    )
}