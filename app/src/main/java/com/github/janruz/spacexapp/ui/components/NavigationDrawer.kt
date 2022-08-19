package com.github.janruz.spacexapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.border
import com.github.janruz.spacexapp.ui.theme.highlight
import com.github.janruz.spacexapp.ui.theme.inactiveRed

data class NavDrawerItem(
    val id: String,
    val title: String,
    @DrawableRes val iconId: Int
)

@Composable
fun NavDrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = "",
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
            val backgroundColor = if(item.id == activeItemId) MaterialTheme.colors.highlight else
                Color.Transparent

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    tint = if(item.id == activeItemId) Color.White else MaterialTheme.colors.onBackground,
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    item.title,
                    color = if(item.id == activeItemId) Color.White else MaterialTheme.colors.onBackground,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}