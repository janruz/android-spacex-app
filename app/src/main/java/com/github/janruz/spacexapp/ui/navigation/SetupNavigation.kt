package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.viewmodels.MainViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    var topBarVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            topBarVisible = destination.route == "/rockets"
        }
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = topBarVisible,
                enter = slideInVertically { -it },
                exit = slideOutVertically { -it }
            ) {
                TopAppBar(
                    backgroundColor = if(isSystemInDarkTheme()) Color.Black else Color.White
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.spacex_logo),
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "/rockets",
            modifier = Modifier.padding(paddingValues)
        ) {

            rocketsComposable(navController, mainViewModel)
            rocketDetailComposable(navController, mainViewModel)
        }
    }

}