package com.github.janruz.spacexapp.ui.components.rockets.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.components.rockets.RocketActiveLabel
import com.github.janruz.spacexapp.ui.components.rockets.RocketSuccessRateLabel
import com.github.janruz.spacexapp.ui.theme.border
import com.github.janruz.spacexapp.ui.theme.spacing
import com.github.janruz.spacexapp.ui.theme.title

/**
 * Displays a card containing basic information about the given rocket
 */
@Composable
fun RocketCard(
    rocket: Rocket,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            elevation = 3.dp,
            border = BorderStroke(width = 1.dp, MaterialTheme.colors.border)
        ) {
            Column {
                AsyncImage(
                    model = rocket.images.firstOrNull(),
                    contentDescription = stringResource(id = R.string.semantics_rocket_image),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .aspectRatio(ratio = 4 / 3f)
                        .fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                        .padding(top = MaterialTheme.spacing.small)
                ) {
                    RocketActiveLabel(
                        isActive = rocket.active,
                        modifier = Modifier
                            .semantics {
                                contentDescription = context.getString(R.string.semantics_rocket_activity)
                            }
                    )

                    RocketSuccessRateLabel(
                        successRate = rocket.successRate,
                        modifier = Modifier
                            .semantics {
                                contentDescription = context.getString(R.string.semantics_rocket_success_rate)
                            }
                    )
                }

                Text(
                    rocket.name,
                    style = MaterialTheme.typography.title,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                        .padding(top = MaterialTheme.spacing.small)
                        .semantics {
                            contentDescription = context.getString(R.string.semantics_rocket_name)
                        }
                )

                Text(
                    rocket.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .semantics {
                            contentDescription =
                                context.getString(R.string.semantics_rocket_description)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
private fun RocketCardPreview() {
    val sampleRocket = Rocket(
        id = "1",
        name = "Falcon 10",
        active = true,
        firstFlight = "2010-06-04",
        description = "Falcon 10 is the next generation of falcons.",
        successRate = 40U,
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        ),
        mass = 8930003293,
        height = 123894f,
        diameter = 12f,
        wikipediaUrl = "https://en.wikipedia.org/wiki/Falcon_Heavy"
    )

    RocketCard(rocket = sampleRocket, onClick = {})
}