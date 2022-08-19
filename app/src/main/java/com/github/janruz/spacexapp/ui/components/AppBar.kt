package com.github.janruz.spacexapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import kotlinx.coroutines.launch

@Composable
fun AppBar(
    onDrawerIconClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = if(isSystemInDarkTheme()) Color.Black else Color.White
    ) {
        IconButton(onClick = onDrawerIconClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = ""
            )
        }

        Image(
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = "",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AnimatedAppBar(
    visible: Boolean,
    onDrawerIconClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it }
    ) {
        AppBar(onDrawerIconClick)
    }
}