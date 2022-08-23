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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.border
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavDrawerItem(
    val id: String,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) : Parcelable

@Composable
fun NavDrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = stringResource(id = R.string.spacex_logo),
            modifier = Modifier
                .padding(
                    top = 48.dp,
                    start = 48.dp,
                    end = 48.dp,
                    bottom = 32.dp
                )
        )

        Divider(
            color = MaterialTheme.colors.border,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )
    }
}

@Composable
fun NavDrawerBody(
    items: List<NavDrawerItem>,
    activeItemId: String,
    onItemClick: (NavDrawerItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
            .fillMaxSize()

    ) {

        items(items) { item ->

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    tint = if(item.id == activeItemId) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(id = item.titleId)
                )

                Spacer(modifier = Modifier.width(16.dp))

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