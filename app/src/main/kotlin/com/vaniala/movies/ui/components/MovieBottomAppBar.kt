package com.vaniala.movies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.vaniala.movies.ui.theme.MoviesTheme

@Composable
fun MovieBottomAppBar(
    selectedItem: BottomAppBarItem,
    items: List<BottomAppBarItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomAppBarItem) -> Unit = {},
) {
    BottomAppBar(
        modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEach {
            NavigationBarItem(
                selected = it == selectedItem,
                onClick = { onItemClick(it) },
                icon = {
                    Column {
                        Icon(
                            it.icon,
                            contentDescription = null,
                        )
                    }
                },
                label = {
                    Text(text = it.label)
                },
            )
        }
    }
}

@Preview
@Composable
private fun MovieBottomAppBarPreview() {
    MoviesTheme {
        Surface {
            MovieBottomAppBar(
                selectedItem = BottomAppBarItem.Profile,
                items = listOf(
                    BottomAppBarItem.Home,
                    BottomAppBarItem.Profile,
                ),
            )
        }
    }
}

sealed class BottomAppBarItem(val label: String, val icon: ImageVector) {
    data object Home : BottomAppBarItem(
        "Início",
        Icons.Default.Home,
    )

    data object Profile : BottomAppBarItem(
        label = "Perfil",
        icon = Icons.Filled.AccountCircle,
    )
}

val bottomAppBarItems = listOf(
    BottomAppBarItem.Home,
    BottomAppBarItem.Profile,
)
