package com.example.englishtutor.ui.navigation.includes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.englishtutor.ui.navigation.drawerItems

@Composable
fun AppDrawer(
    onItemSelected: (String) -> Unit
) {
    ModalDrawerSheet {
        Column(modifier = Modifier.padding(16.dp)) {
            drawerItems.forEach { screen ->
                NavigationDrawerItem(
                    label = { Text(screen.title) },
                    icon = {
                        screen.icon?.let {
                            Icon(imageVector = it, contentDescription = screen.title)
                        }
                    },
                    selected = false,
                    onClick = { onItemSelected(screen.route) }
                )
            }
        }
    }
}