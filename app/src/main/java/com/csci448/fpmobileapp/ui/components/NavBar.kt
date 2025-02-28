package com.csci448.fpmobileapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavBar(){
    BottomAppBar {
        BottomNavigation {
            BottomNavigationItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = false,
                icon = {},
                onClick = {}
            )
        }
    }
}