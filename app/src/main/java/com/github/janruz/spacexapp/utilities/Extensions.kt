package com.github.janruz.spacexapp.utilities

import androidx.navigation.NavDestination
import com.github.janruz.spacexapp.ui.navigation.NavConstants.ROCKET_DETAIL_SCREEN_PREFIX

val NavDestination.isRocketDetail: Boolean get() {
    return this.route?.startsWith(ROCKET_DETAIL_SCREEN_PREFIX) ?: false
}