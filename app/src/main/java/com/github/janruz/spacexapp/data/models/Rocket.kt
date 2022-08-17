package com.github.janruz.spacexapp.data.models

import com.squareup.moshi.Json

data class Rocket(
    val id: String,
    val name: String,
    val active: Boolean,
    val firstFlight: String,
    val description: String,
    val images: List<String>
)

data class RocketFromApi(

    @field:Json(name = "id")
    val id: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "active")
    val active: Boolean,

    @field:Json(name = "first_flight")
    val firstFlight: String,

    @field:Json(name = "description")
    val description: String,

    @field:Json(name = "flickr_images")
    val images: List<String>
)

val RocketFromApi.asRocket: Rocket get() {
    return Rocket(
        id = this.id,
        name = this.name,
        active = this.active,
        firstFlight = this.firstFlight,
        description = this.description,
        images = this.images
    )
}

val List<RocketFromApi>.asRockets: List<Rocket> get() {
    return map { it.asRocket }
}