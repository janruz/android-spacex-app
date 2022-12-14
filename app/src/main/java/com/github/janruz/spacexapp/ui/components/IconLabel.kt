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
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * Displays the given text along with an icon aligned in a row (icon - text).
 * @param color the color to be used for the text and the icon
 */
@Composable
fun IconLabel(
    text: String,
    iconPainter: Painter,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = text,
            tint = color
        )

        Text(
            text = text,
            color = color,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .padding(
                    vertical = MaterialTheme.spacing.xxSmall,
                    horizontal = MaterialTheme.spacing.xSmall
                )
        )
    }
}