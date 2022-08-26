package com.github.janruz.spacexapp.data.models

import com.squareup.moshi.Json

/**
 * The Company model class used throughout the application.
 */
data class Company(
    val id: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val launchSites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    val valuation: Long,
    val summary: String
)

/**
 * The Company class representing the company data we get from the API.
 */
data class CompanyFromApi(

    @field:Json(name = "id")
    val id: String,

    @field:Json(name = "founder")
    val founder: String,

    @field:Json(name = "founded")
    val founded: Int,

    @field:Json(name = "employees")
    val employees: Int,

    @field:Json(name = "launch_sites")
    val launchSites: Int,

    @field:Json(name = "ceo")
    val ceo: String,

    @field:Json(name = "cto")
    val cto: String,

    @field:Json(name = "coo")
    val coo: String,

    @field:Json(name = "valuation")
    val valuation: Long,

    @field:Json(name = "summary")
    val summary: String
)

val CompanyFromApi.asCompany: Company get() {
    return Company(
        id = this.id,
        founder = this.founder,
        founded = this.founded,
        employees = this.employees,
        launchSites = this.launchSites,
        ceo = this.ceo,
        cto = this.cto,
        coo = this.coo,
        valuation = this.valuation,
        summary = this.summary
    )
}