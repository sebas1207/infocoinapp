package com.example.infocoinapp.views

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object AssetsList : Screen("assets_list", Icons.Default.List, "Assets")
    object Settings   : Screen("settings",   Icons.Default.Settings, "Settings")
}

@Composable
fun BottomTabBar(navController: NavHostController) {
    val items = listOf(
        Screen.AssetsList,
        Screen.Settings
    )

    BottomAppBar {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            // Mantenemos una sola instancia en el back stack
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
