package com.github.janruz.spacexapp.ui.components

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.border
import com.github.janruz.spacexapp.ui.theme.spacing
import kotlinx.parcelize.Parcelize

/**
 * Represents a single menu item in the navigation drawer
 * @param id the unique identifier of this menu item (ideally the navigation route
 * of the corresponding destination).
 * @param titleId the string resource id of the title shown in the drawer
 */
@Parcelize
data class NavDrawerItem(
    val id: String,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) : Parcelable

/**
 * Defines the header of navigation drawer.
 */
@Composable
fun NavDrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Image(
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = stringResource(id = R.string.semantics_spacex_logo),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.xxLarge,
                    start = MaterialTheme.spacing.xxLarge,
                    end = MaterialTheme.spacing.xxLarge,
                    bottom = MaterialTheme.spacing.xLarge
                )
        )

        Divider(
            color = MaterialTheme.colors.border,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.xLarge)
        )
    }
}

/**
 * Defines the UI of the menu list in the navigation drawer.
 * @param items the list of all the nav drawer items to be displayed
 * @param activeItemId the id of the nav drawer item where the user is currently located
 * @param onItemClick fires when a user clicks on a given nav drawer item
 */
@Composable
fun NavDrawerBody(
    items: List<NavDrawerItem>,
    activeItemId: String,
    onItemClick: (NavDrawerItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {

        items(items) { item ->

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(MaterialTheme.spacing.medium)
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    tint = if(item.id == activeItemId) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(id = item.titleId)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Text(
                    text = stringResource(id = item.titleId),
                    color = if(item.id == activeItemId) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}