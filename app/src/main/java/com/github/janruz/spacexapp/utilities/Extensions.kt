package com.github.janruz.spacexapp.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateFormat
import androidx.compose.runtime.MutableState
import androidx.navigation.NavDestination
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.ui.navigation.NavConstants.ROCKET_DETAIL_SCREEN_PREFIX
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

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

fun String.formatAsDate(context: Context): String {
    val parsed = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(this)

    return parsed?.let {
        DateFormat
            .getMediumDateFormat(context)
            .format(it)
    } ?: this
}

fun Rocket.openWikipedia(context: Context) {
    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(wikipediaUrl)
    })
}