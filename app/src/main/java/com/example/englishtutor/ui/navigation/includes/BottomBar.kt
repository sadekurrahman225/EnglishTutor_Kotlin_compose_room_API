package com.example.englishtutor.ui.navigation.includes

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.englishtutor.ui.navigation.bottomBarItems

@Composable
fun BottomBar(
    currentRoute: String?,
    onItemSelected: (String) -> Unit
) {
    NavigationBar {
        bottomBarItems.forEach { screen ->
            NavigationBarItem(
                label = { Text(screen.title) },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onItemSelected(screen.route) }
            )
        }
    }
}