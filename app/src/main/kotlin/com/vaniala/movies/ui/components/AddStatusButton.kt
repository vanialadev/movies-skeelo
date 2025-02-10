package com.vaniala.movies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddStatusButton(toggleButton: ToggleButton, onClick: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape,
                )
                .align(Center)
                .clip(CircleShape)
                .clickable {
                    onClick()
                }
                .padding(8.dp),
        ) {
            Row(
                Modifier.align(Center),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    toggleButton.icon,
                    contentDescription = null,
                    Modifier.size(43.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(text = toggleButton.text, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

class ToggleButton(val text: String, val icon: ImageVector, val action: () -> Unit)

@Preview(showBackground = true)
@Composable
private fun AddStatusButtonPreview() {
    AddStatusButton(ToggleButton("Favoritar", Icons.Outlined.FavoriteBorder, {}))
}
