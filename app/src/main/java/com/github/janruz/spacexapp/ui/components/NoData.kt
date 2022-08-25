package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * Displays the given message filling the width of the entire entire screen. It is basically
 * a helper composable containing UI made for displaying "no data" messages such as when
 * user picks such a combination of rocket filters that there are no rockets that match them.
 */
@Composable
fun NoData(
    message: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1
        )
    }

}

@Preview
@Composable
fun NoDataPreview() {
    NoData(message = "There is no data here.")
}