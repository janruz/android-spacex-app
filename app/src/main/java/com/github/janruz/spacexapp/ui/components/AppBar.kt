package com.github.janruz.spacexapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R

@Composable
fun TopBar(
    showLogo: Boolean,
    title: String?,
    onDrawerIconClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        IconButton(onClick = onDrawerIconClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = ""
            )
        }

        if(showLogo || title == null) {
            Image(
                painter = painterResource(id = R.drawable.spacex_logo),
                contentDescription = "",
                modifier = Modifier.padding(16.dp)
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

@Composable
fun AnimatedAppBar(
    visible: Boolean,
    showLogo: Boolean,
    title: String?,
    onDrawerIconClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it }
    ) {
        TopBar(showLogo, title, onDrawerIconClick)
    }
}