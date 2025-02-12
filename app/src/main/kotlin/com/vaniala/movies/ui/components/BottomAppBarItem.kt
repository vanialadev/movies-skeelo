package com.vaniala.movies.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomAppBarItem(val label: String, val icon: ImageVector) {
    data object Home : BottomAppBarItem("Início", Icons.Default.Home)
    data object Search : BottomAppBarItem("Busca", Icons.Default.Search)
    data object Profile : BottomAppBarItem("Perfil", Icons.Default.Person)
}

val bottomAppBarItems = listOf(
    BottomAppBarItem.Home,
    BottomAppBarItem.Search,
    BottomAppBarItem.Profile,
)
