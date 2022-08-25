package com.github.janruz.spacexapp.ui.components

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable

/**
 * Makes the given composable span across the entire width of the lazy grid
 */
fun LazyGridScope.maxLineSpanItem(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(
        span = {
            GridItemSpan(maxLineSpan)
        },
        content = content
    )
}