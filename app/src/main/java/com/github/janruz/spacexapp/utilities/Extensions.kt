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
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper extension indicating if the destination is the rocket detail screen or not
 */
val NavDestination.isRocketDetail: Boolean get() {
    return this.route?.startsWith(ROCKET_DETAIL_SCREEN_PREFIX) ?: false
}

/**
 * Sets the status to LOADING but if its previous value was false, then it delays execution
 * by 1 second so that the UI is showing loading indicator for at least 1 second to avoid
 * flickering between loading and error screen.
 */
suspend fun MutableState<Status>.loading() {

    if(value == Status.FAILURE) {
        value = Status.LOADING
        delay(1000)
    } else {
        value = Status.LOADING
    }
}

/**
 * Formats the given string according to the medium date format with the default locale
 */
fun String.formatAsDate(context: Context): String {
    val parsed = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(this)

    return parsed?.let {
        DateFormat
            .getMediumDateFormat(context)
            .format(it)
    } ?: this
}

/**
 * Formats the given number as the specified currency according to the default locale
 */
fun Long.formatAsCurrency(currency: Currency = Currency.getInstance(Locale.US)): String {
    return NumberFormat
        .getCurrencyInstance(Locale.getDefault())
        .apply {
            this.currency = currency
        }
        .format(this)
}

/**
 * Formats the given number according to the default locale
 */
fun Number.formatAsNumber(): String {
    return NumberFormat
        .getNumberInstance(Locale.getDefault())
        .format(this)
}

/**
 * Formats the given float as percent
 * @param alreadyInPercent indicates if the given value is already a percent value
 * (a value between 0 and 100) or a decimal value representing percent (a value between 0f and 1f).
 */
fun Float.formatAsPercent(alreadyInPercent: Boolean): String {
    return NumberFormat
        .getPercentInstance(Locale.getDefault())
        .format(if(alreadyInPercent) this / 100f else this)
}

/**
 * Fires an intent trying to display the Wikipedia page of the given rocket.
 */
fun Rocket.openWikipedia(context: Context) {
    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(wikipediaUrl)
    })
}