package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.spacing

@Composable
fun CloseIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick, modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_clear),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(alpha = 0.5f, red = 0f, green = 0f, blue = 0f))
                .padding(MaterialTheme.spacing.xxSmall)
        )
    }
}