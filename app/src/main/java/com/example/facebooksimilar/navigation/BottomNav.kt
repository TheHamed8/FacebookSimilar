package com.example.facebooksimilar.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavController, onDrawerIconClick: () -> Unit) {
    NavigationBar(
        containerColor = Color.Blue.copy(alpha = 0.8f),
        modifier = Modifier.height(72.dp)
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Home.route,
            onClick = { navController.navigate(Destination.Home.route) },
            icon = {
                androidx.compose.material.Icon(
                    Icons.Default.Home,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { Text(text = Destination.Home.route, color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                selectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )

        )

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Notification.route,
            onClick = { navController.navigate(Destination.Notification.route) },
            icon = {
                androidx.compose.material.Icon(
                    Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { Text(text = Destination.Notification.route, color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = onDrawerIconClick,
            icon = {
                androidx.compose.material.Icon(
                    Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { androidx.compose.material.Text(text = "Menu", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                selectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
    }
}