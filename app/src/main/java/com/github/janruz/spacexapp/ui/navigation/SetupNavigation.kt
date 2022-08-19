package com.github.janruz.spacexapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.*
import com.github.janruz.spacexapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun SetupNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    var appBarVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            appBarVisible = destination.route == "/rockets"
        }
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navDrawerItems = remember {
        listOf(
            NavDrawerItem("Company Info", "About SpaceX", R.drawable.ic_business),
            NavDrawerItem("Rockets", "Rockets", R.drawable.ic_rocket),
            NavDrawerItem("About the App", "About the App", R.drawable.ic_info)
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AnimatedAppBar(
                visible = appBarVisible,
                onDrawerIconClick = {
                    scope.launch { scaffoldState.drawerState.open() }
                }
            )
        },
        drawerContent = {
            NavDrawerHeader()
            NavDrawerBody(
                items = navDrawerItems,
                activeItemId = navDrawerItems[1].id,
                onItemClick = {}
            )
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