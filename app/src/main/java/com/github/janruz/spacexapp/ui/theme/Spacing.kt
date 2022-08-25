package com.github.janruz.spacexapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Represents a set of different spacing values used throughout the UI of the application.
 */
data class Spacing(
    val default: Dp = 0.dp,
    val xxSmall: Dp = 4.dp,
    val xSmall: Dp = 8.dp,
    val small: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp,
    val xxLarge: Dp = 48.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

/**
 * An extension allowing access to the spacing values via MaterialTheme object.
 * It is not necessary to extend it though. It is done just for the sake of consistency
 * with other theme related values already provided (MaterialTheme.colors...).
 */
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current