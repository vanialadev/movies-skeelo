package com.vaniala.movies.extensions

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.vaniala.movies.R

fun Context.getTabTitle(): List<Pair<String, ImageVector>> = listOf(
    getString(R.string.favorites) to Icons.Default.Favorite,
    getString(R.string.watchlist) to Icons.Default.DateRange,
)
