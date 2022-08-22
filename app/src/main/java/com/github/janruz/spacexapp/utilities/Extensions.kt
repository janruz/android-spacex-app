package com.github.janruz.spacexapp.utilities

import androidx.compose.runtime.MutableState
import androidx.navigation.NavDestination
import com.github.janruz.spacexapp.ui.navigation.NavConstants.ROCKET_DETAIL_SCREEN_PREFIX
import kotlinx.coroutines.delay

val NavDestination.isRocketDetail: Boolean get() {
    return this.route?.startsWith(ROCKET_DETAIL_SCREEN_PREFIX) ?: false
}

fun<T> Result<T?>.ifSuccessGetOrNull(action: (T) -> Unit): Result<T?> {
    if(isSuccess) {
        getOrNull()?.let {
            action(it)
        }
    }
    return this
}

suspend fun MutableState<Status>.loading() {
    // if the previous status is failure, add 1 second delay so that the UI is showing loading
    // indicator for at least 1 second to avoid flickering between loading and error screen
    if(value == Status.FAILURE) {
        value = Status.LOADING
        delay(1000)
    } else {
        value = Status.LOADING
    }
}