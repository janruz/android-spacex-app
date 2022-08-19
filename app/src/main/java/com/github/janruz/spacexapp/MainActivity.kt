package com.github.janruz.spacexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.github.janruz.spacexapp.ui.screens.MainScreen
import com.github.janruz.spacexapp.ui.theme.SpaceXAppTheme
import com.github.janruz.spacexapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            SpaceXAppTheme {
                MainScreen(navController, mainViewModel)
            }
        }
    }
}