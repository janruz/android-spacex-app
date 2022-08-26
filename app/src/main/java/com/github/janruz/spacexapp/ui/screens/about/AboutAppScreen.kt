package com.github.janruz.spacexapp.ui.screens.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.spacing

/**
 * A screen showing information about this app and its purpose
 */
@Composable
fun AboutAppScreen() {
    Text(
        text = stringResource(id = R.string.about_app_information),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
    )
}

@Preview
@Composable
fun AboutAppScreenPreview() {
    AboutAppScreen()
}