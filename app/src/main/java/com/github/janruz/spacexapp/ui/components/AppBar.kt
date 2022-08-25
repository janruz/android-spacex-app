package com.github.janruz.spacexapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * Represents the top app bar used throughout the application.
 * @param showLogo should show the SpaceX logo or not
 * @param title the text to be shown in the app bar instead of the logo
 * @param onDrawerIconClick fires when the user clicks on the hamburger icon
 */
@Composable
fun AppBar(
    showLogo: Boolean,
    title: String?,
    onDrawerIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        IconButton(onClick = onDrawerIconClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = stringResource(id = R.string.semantics_toggle_nav_drawer)
            )
        }

        if(showLogo || title == null) {
            Image(
                painter = painterResource(id = R.drawable.spacex_logo),
                contentDescription = stringResource(id = R.string.semantics_spacex_logo),
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            )
        } else {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

/**
 * Adds an enter and an exit animation to the AppBar
 * @param visible should the app bar be visible
 * @param showLogo should show the SpaceX logo or not
 * @param title the text to be shown in the app bar instead of the logo
 * @param onDrawerIconClick fires when the user clicks on the hamburger icon
 */
@Composable
fun AnimatedAppBar(
    visible: Boolean,
    showLogo: Boolean,
    title: String?,
    onDrawerIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it },
        modifier = modifier
    ) {
        AppBar(showLogo, title, onDrawerIconClick)
    }
}