package com.github.janruz.spacexapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.github.janruz.spacexapp.ui.components.AnimatedAppBar
import com.github.janruz.spacexapp.ui.components.NavDrawerBody
import com.github.janruz.spacexapp.ui.components.NavDrawerHeader
import com.github.janruz.spacexapp.ui.navigation.NavConstants
import com.github.janruz.spacexapp.ui.navigation.Navigator
import com.github.janruz.spacexapp.ui.navigation.SetupNavigation
import com.github.janruz.spacexapp.ui.navigation.getNavItemOfRoute
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.utilities.isRocketDetail
import kotlinx.coroutines.launch

/**
 * The parent composable hosting all the app's UI
 */
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val navigator = remember(navController) {
        Navigator(navController)
    }

    var appBarVisible by rememberSaveable { mutableStateOf(true) }

    var currentDrawerItem by rememberSaveable { mutableStateOf(NavConstants.DRAWER_ITEMS.first()) }

    DisposableEffect(navController) {
        val navListener = NavController.OnDestinationChangedListener { _, destination, _ ->
            appBarVisible = !destination.isRocketDetail

            destination.route?.let { route ->

                NavConstants.DRAWER_ITEMS.getNavItemOfRoute(route)?.let { item ->
                    currentDrawerItem = item
                }
            }
        }

        navController.addOnDestinationChangedListener(navListener)

        onDispose {
            navController.removeOnDestinationChangedListener(navListener)
        }
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AnimatedAppBar(
                visible = appBarVisible,
                showLogo = currentDrawerItem.id == NavConstants.COMPANY_SECTION,
                title = stringResource(id = currentDrawerItem.titleId),
                onDrawerIconClick = {
                    scope.launch { scaffoldState.drawerState.open() }
                }
            )
        },
        drawerShape = RectangleShape,
        drawerContent = {
            NavDrawerHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            )
            NavDrawerBody(
                items = NavConstants.DRAWER_ITEMS,
                activeItemId = currentDrawerItem.id,
                onItemClick = { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                        navigator.toDrawerItem(item)
                    }
                },
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxSize()
            )
        }
    ) { paddingValues ->
        SetupNavigation(navController, navigator, paddingValues)
    }
}