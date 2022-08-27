package com.github.janruz.spacexapp.ui.components.rockets.list

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.utilities.formatAsPercent
import org.junit.Rule
import org.junit.Test
import com.github.janruz.spacexapp.R

class RocketCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun passRocket_shouldDisplayRocketInformationInTheProperComposables() {
        composeRule.setContent {
            RocketCard(
                rocket = newMockRocket(),
                onClick = {}
            )
        }

        val context = ApplicationProvider.getApplicationContext<Context>()

        val rocket = newMockRocket()
        composeRule
            .onNodeWithContentDescription(
                label = context.getString(R.string.semantics_rocket_name),
                useUnmergedTree = true
            )
            .assertExists()
            .assertTextEquals(rocket.name)

        composeRule
            .onNodeWithContentDescription(
                label = context.getString(R.string.semantics_rocket_description),
                useUnmergedTree = true
            )
            .assertExists()
            .assert(hasText(text = rocket.description, substring = true))

        composeRule
            .onNodeWithContentDescription(
                label = context.getString(R.string.semantics_rocket_activity),
                useUnmergedTree = true
            )
            .assertExists()
            .assert(hasAnyChild(hasText(context.getString(R.string.rocket_active))))

        composeRule
            .onNodeWithContentDescription(
                label = context.getString(R.string.semantics_rocket_success_rate),
                useUnmergedTree = true
            )
            .assertExists()
            .assert(
                hasAnyChild(
                    hasText(rocket.successRate.toFloat().formatAsPercent(alreadyInPercent = true))
                )
            )
    }

    private fun newMockRocket(): Rocket {
        return Rocket(
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
    }
}