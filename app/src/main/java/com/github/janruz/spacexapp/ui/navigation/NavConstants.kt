package com.github.janruz.spacexapp.ui.navigation

import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.NavDrawerItem

/**
 * Defines constants related to navigation
 */
object NavConstants {

    const val COMPANY_SECTION = "/company"
    const val ROCKETS_SECTION = "/rockets"
    const val ABOUT_APP_SECTION = "/about"

    const val COMPANY_INFO_SCREEN = "$COMPANY_SECTION/info"

    const val ROCKETS_LIST_SCREEN = "$ROCKETS_SECTION/all"
    const val ROCKET_ID_KEY = "rocketId"
    const val ROCKET_DETAIL_SCREEN_PREFIX = "$ROCKETS_SECTION/detail"
    const val ROCKET_DETAIL_SCREEN = "$ROCKET_DETAIL_SCREEN_PREFIX/{$ROCKET_ID_KEY}"

    const val ABOUT_APP_SCREEN = "$ABOUT_APP_SECTION/app"

    val DRAWER_ITEMS = listOf(
        NavDrawerItem(
            id = COMPANY_SECTION,
            defaultRoute = COMPANY_INFO_SCREEN,
            titleId = R.string.screen_title_company_info,
            iconId = R.drawable.ic_business
        ),
        NavDrawerItem(
            id = ROCKETS_SECTION,
            defaultRoute = ROCKETS_LIST_SCREEN,
            titleId = R.string.screen_title_rockets,
            iconId = R.drawable.ic_rocket
        ),
        NavDrawerItem(
            id = ABOUT_APP_SECTION,
            defaultRoute = ABOUT_APP_SCREEN,
            titleId = R.string.screen_title_about_app,
            iconId = R.drawable.ic_info
        )
    )
}

/**
 * Returns a nav drawer item which the given route belongs to.
 */
fun List<NavDrawerItem>.getNavItemOfRoute(route: String): NavDrawerItem? {
    val section = when {
        route.startsWith(NavConstants.COMPANY_SECTION) -> NavConstants.COMPANY_SECTION
        route.startsWith(NavConstants.ROCKETS_SECTION) -> NavConstants.ROCKETS_SECTION
        route.startsWith(NavConstants.ABOUT_APP_SECTION) -> NavConstants.ABOUT_APP_SECTION
        else -> null
    }

    return section?.let { safeSection ->
        find { it.id == safeSection }
    }
}