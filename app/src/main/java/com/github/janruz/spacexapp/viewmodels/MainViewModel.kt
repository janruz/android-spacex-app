package com.github.janruz.spacexapp.viewmodels

import androidx.lifecycle.ViewModel
import com.github.janruz.spacexapp.models.Rocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _rockets = MutableStateFlow(mockRockets)
    val rockets = _rockets.asStateFlow()
}

private val mockRockets = listOf(
    Rocket(
        id = "5e9d0d95eda69974db09d1ed",
        name = "Falcon Heavy",
        active = true,
        firstFlight = "2018-02-06",
        description = "With the ability to lift into orbit over 54 metric tons (119,000 lb)--a mass equivalent to a 737 jetliner loaded with passengers, crew, luggage and fuel--Falcon Heavy can lift more than twice the payload of the next closest operational vehicle, the Delta IV Heavy, at one-third the cost.",
        images = listOf(
            "https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg"
        )
    ),
    Rocket(
        id = "5e9d0d95eda69973a809d1ec",
        name = "Falcon 9",
        active = true,
        firstFlight = "2010-06-04",
        description = "Falcon 9 is a two-stage rocket designed and manufactured by SpaceX for the reliable and safe transport of satellites and the Dragon spacecraft into orbit.",
        images = listOf(
            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
        )
    ),
    Rocket(
        id = "5e9d0d96eda699382d09d1ee",
        name = "Starship",
        active = false,
        firstFlight = "2021-12-01",
        description = "Starship and Super Heavy Rocket represent a fully reusable transportation system designed to service all Earth orbit needs as well as the Moon and Mars. This two-stage vehicle — composed of the Super Heavy rocket (booster) and Starship (ship) — will eventually replace Falcon 9, Falcon Heavy and Dragon.",
        images = listOf(
            "https://live.staticflickr.com/65535/48954138962_ee541e6755_b.jpg"
        )
    )
)