package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InfoColumn(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}