package com.github.janruz.spacexapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.github.janruz.spacexapp.ui.screens.MainScreen
import com.github.janruz.spacexapp.ui.theme.SpaceXAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The one and only activity hosting the UI of the entire app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            SpaceXAppTheme {
                MainScreen(navController)
            }
        }
    }
}