package com.github.janruz.spacexapp.data.models

import com.squareup.moshi.Json

data class Rocket(
    val id: String,
    val name: String,
    val active: Boolean,
    val firstFlight: String,
    val successRate: UInt,
    val description: String,
    val images: List<String>,
    val mass: Long,
    val height: Float,
    val diameter: Float,
    val wikipediaUrl: String
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

    @field:Json(name = "success_rate_pct")
    val successRate: UInt,

    @field:Json(name = "description")
    val description: String,

    @field:Json(name = "flickr_images")
    val images: List<String>,

    @field:Json(name = "mass")
    val mass: Mass,

    @field:Json(name = "height")
    val height: Dimension,

    @field:Json(name = "diameter")
    val diameter: Dimension,

    @field:Json(name = "wikipedia")
    val wikipedia: String
) {

    data class Mass(
        @field:Json(name = "kg")
        val kg: Long
    )

    data class Dimension(
        @field:Json(name = "meters")
        val meters: Float
    )
}

val RocketFromApi.asRocket: Rocket get() {
    return Rocket(
        id = this.id,
        name = this.name,
        active = this.active,
        firstFlight = this.firstFlight,
        successRate = this.successRate,
        description = this.description,
        images = this.images,
        mass = this.mass.kg,
        height = this.height.meters,
        diameter = this.diameter.meters,
        wikipediaUrl = this.wikipedia
    )
}

val List<RocketFromApi>.asRockets: List<Rocket> get() {
    return map { it.asRocket }
}