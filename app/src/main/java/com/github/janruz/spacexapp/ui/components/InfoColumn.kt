package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.github.janruz.spacexapp.ui.theme.label

/**
 * Displays a text along with a label describing the meaning of the text aligned in a column.
 */
@Composable
fun InfoColumn(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.label,
            color = MaterialTheme.colors.onBackground,
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}