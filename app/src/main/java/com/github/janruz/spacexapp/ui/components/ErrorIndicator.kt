package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.janruz.spacexapp.R

@Composable
fun ErrorIndicator(
    message: String,
    onTryAgain: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                tint = MaterialTheme.colors.error,
                contentDescription = stringResource(id = R.string.error_icon),
                modifier = Modifier
                    .size(40.dp)
            )

            Text(
                text = message,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 18.sp
            )
            
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.error
                ),
                onClick = onTryAgain
            ) {
                Text(text = "Try again")
            }
        }
    }
}

@Preview
@Composable
fun ErrorIndicatorPreview() {
    ErrorIndicator(
        message = "Oops, something has gone wrong.",
        onTryAgain = {}
    )
}