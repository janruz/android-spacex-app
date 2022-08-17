package com.github.janruz.spacexapp.models

data class Rocket(
    val id: String,
    val name: String,
    val active: Boolean,
    val firstFlight: String,
    val description: String,
    val images: List<String>
)