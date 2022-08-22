package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.positive
import com.github.janruz.spacexapp.ui.theme.negative
import com.github.janruz.spacexapp.ui.theme.neutral

@Composable
fun RocketActiveLabel(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {

    IconLabel(
        text = stringResource(id = if(isActive) R.string.rocket_active else R.string.rocket_inactive),
        iconPainter = painterResource(id = if(isActive) R.drawable.ic_check else R.drawable.ic_clear),
        color = if (isActive) MaterialTheme.colors.positive else MaterialTheme.colors.negative,
        modifier = modifier
    )
}