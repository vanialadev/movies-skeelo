package com.vaniala.movies.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vaniala.movies.R

@Composable
fun ThemeIcon(isDarkTheme: Boolean, onThemeUpdated: () -> Unit) {
    IconButton(onClick = onThemeUpdated) {
        Icon(
            imageVector = if (isDarkTheme) {
                Icons.Default.LightMode
            } else {
                Icons.Default.DarkMode
            },
            contentDescription = stringResource(R.string.change_theme),
        )
    }
}
