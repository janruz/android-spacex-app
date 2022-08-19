package com.github.janruz.spacexapp.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.github.janruz.spacexapp.ui.components.AnimatedAppBar
import com.github.janruz.spacexapp.ui.components.NavDrawerBody
import com.github.janruz.spacexapp.ui.components.NavDrawerHeader
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.navigation.SetupNavigation
import com.github.janruz.spacexapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    var appBarVisible by remember { mutableStateOf(true) }

    var activeDrawerItem by remember { mutableStateOf(NavConstants.DRAWER_ITEMS.first().id) }

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { route ->
                appBarVisible = !route.startsWith("/rockets/")

                activeDrawerItem = route
            }
        }
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                items = NavConstants.DRAWER_ITEMS,
                activeItemId = activeDrawerItem,
                onItemClick = { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                        navController.navigate(item.id)
                    }
                }
            )
        }
    ) { paddingValues ->
        SetupNavigation(navController, mainViewModel, paddingValues)
    }
}