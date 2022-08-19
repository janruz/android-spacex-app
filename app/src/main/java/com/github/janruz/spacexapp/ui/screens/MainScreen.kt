package com.github.janruz.spacexapp.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.github.janruz.spacexapp.ui.components.AnimatedAppBar
import com.github.janruz.spacexapp.ui.components.NavDrawerBody
import com.github.janruz.spacexapp.ui.components.NavDrawerHeader
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.navigation.SetupNavigation
import com.github.janruz.spacexapp.utilities.isRocketDetail
import com.github.janruz.spacexapp.viewmodels.RocketsViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController
) {
    var appBarVisible by remember { mutableStateOf(true) }

    var activeDrawerItem by remember { mutableStateOf(NavConstants.DRAWER_ITEMS.first()) }

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            appBarVisible = !destination.isRocketDetail

            destination.route?.let { route ->

                NavConstants.DRAWER_ITEMS.find { it.id == route }?.let { item ->
                    activeDrawerItem = item
                }
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
                showLogo = activeDrawerItem.id == NavConstants.COMPANY_INFO_SCREEN,
                title = stringResource(id = activeDrawerItem.titleId),
                onDrawerIconClick = {
                    scope.launch { scaffoldState.drawerState.open() }
                }
            )
        },
        drawerContent = {
            NavDrawerHeader()
            NavDrawerBody(
                items = NavConstants.DRAWER_ITEMS,
                activeItemId = activeDrawerItem.id,
                onItemClick = { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                        navController.navigate(item.id)
                    }
                }
            )
        }
    ) { paddingValues ->
        SetupNavigation(navController, paddingValues)
    }
}