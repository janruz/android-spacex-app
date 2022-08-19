package com.github.janruz.spacexapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.janruz.spacexapp.R
import com.github.janruz.spacexapp.ui.theme.border

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
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (NavDrawerItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
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
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}