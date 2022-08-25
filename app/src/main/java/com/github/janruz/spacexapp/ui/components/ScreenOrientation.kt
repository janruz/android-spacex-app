package com.github.janruz.spacexapp.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

enum class ScreenOrientation {
    PORTRAIT, LANDSCAPE
}

/**
 * Remembers and returns the current screen orientation
 */
@Composable
fun rememberScreenOrientation(): ScreenOrientation {
    val configuration = LocalConfiguration.current

    return remember(configuration) {
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ScreenOrientation.LANDSCAPE
        } else {
            ScreenOrientation.PORTRAIT
        }
    }
}