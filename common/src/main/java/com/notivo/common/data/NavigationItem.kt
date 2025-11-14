package com.notivo.common.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.notivo.common.navigation.Destination

data class NavigationItem(
    val title: String,
    val route: Destination,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
) {
    companion object {
        fun getNavigationList(): List<NavigationItem> {
            return listOf(
                NavigationItem(
                    title = "Home",
                    route = Destination.Home,
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home
                ),
                NavigationItem(
                    title = "MyLists",
                    route = Destination.MyLists,
                    selectedIcon = Icons.AutoMirrored.Filled.List,
                    unselectedIcon = Icons.AutoMirrored.Outlined.List
                ),
            )
        }
    }
}


