package com.csci448.fpmobileapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.graphics.createBitmap

@Composable
fun NavBar(){
    // TODO: pass in view model with status on which icon is selected
    BottomAppBar {
        BottomNavigation {
            BottomNavigationItem(
                label = {
                    Text(
                        text = "Home"
                    )
                },
                selected = false, // TODO: set status on which screen is active
                icon = {
                    /*
                    Icon(
                        // TODO: fill in icon
                    )
                     */
                },
                onClick = {
                    // TODO: navigate to screen represented by button
                }
            )
            // TODO: more BottomNavigationItems for each screen in menu
        }
    }
}