package com.github.janruz.spacexapp.ui.navigation

import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.components.NavDrawerItem

object NavConstants {

    const val COMPANY_INFO_SCREEN = "/companyInfo"

    const val ROCKETS_LIST_SCREEN = "/rockets"
    const val ROCKET_ID_KEY = "rocketId"
    const val ROCKET_DETAIL_SCREEN = "/rockets/{$ROCKET_ID_KEY}"
    const val ABOUT_APP_SCREEN = "/aboutApp"

    val DRAWER_ITEMS = listOf(
        NavDrawerItem(
            id = COMPANY_INFO_SCREEN,
            titleId = R.string.screen_title_company_info,
            iconId = R.drawable.ic_business
        ),
        NavDrawerItem(
            id = ROCKETS_LIST_SCREEN,
            titleId = R.string.screen_title_rockets,
            iconId = R.drawable.ic_rocket
        ),
        NavDrawerItem(
            id = ABOUT_APP_SCREEN,
            titleId = R.string.screen_title_about_app,
            iconId = R.drawable.ic_info
        )
    )
}