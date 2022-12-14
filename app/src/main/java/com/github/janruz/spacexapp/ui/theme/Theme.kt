package com.github.janruz.spacexapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = Colors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Red,
    secondaryVariant = Red,
    background = Dark,
    surface = Dark,
    error = LightError,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black,
    isLight = false
)

private val LightColorPalette = Colors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Red,
    secondaryVariant = Red,
    background = Color.White,
    surface = Color.White,
    error = DarkError,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    isLight = true
)

val Colors.border: Color get() {
    return if(isLight) Color.LightGray else Color.DarkGray
}

val Colors.positive: Color get() {
    return Green
}

val Colors.negative: Color get() {
    return Red
}

val Colors.neutral: Color get() {
    return Orange
}

@Composable
fun SpaceXAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}