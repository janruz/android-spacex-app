package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.activeGreen
import com.github.janruz.spacexapp.ui.theme.inactiveRed

@Composable
fun RocketActiveLabel(
    isActive: Boolean
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
    ) {
        Text(
            text = stringResource(id = if(isActive) R.string.rocket_active else R.string.rocket_inactive),
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (isActive) MaterialTheme.colors.activeGreen else MaterialTheme.colors.inactiveRed)
                .padding(vertical = 4.dp, horizontal = 6.dp)
        )
    }
}