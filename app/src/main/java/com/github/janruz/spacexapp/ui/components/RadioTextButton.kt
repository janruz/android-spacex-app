package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun RadioTextButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Text(
            text,
            color = MaterialTheme.colors.onBackground
        )
    }
}