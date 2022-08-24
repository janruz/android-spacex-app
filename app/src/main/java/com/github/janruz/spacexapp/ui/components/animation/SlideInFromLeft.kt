package com.github.janruz.spacexapp.ui.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable

@Composable
fun SlideInFromLeft(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = 200), initialOffsetX = { -it }),
        content = content
    )
}