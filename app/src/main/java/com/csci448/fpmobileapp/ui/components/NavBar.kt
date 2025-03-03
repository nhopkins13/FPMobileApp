package com.csci448.fpmobileapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavBar(){
    // TODO: pass in view model with status on which icon is selected
    BottomAppBar {
        BottomNavigation {
            BottomNavigationItem(
                label = {
                    Text(text = "Home")
                },
                selected = false, // TODO: set status on which screen is active
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                },
                onClick = {
                    // TODO: navigate to screen represented by button
                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = "Shop")
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shop"
                    )
                },
                onClick = {
                    // TODO: navigate to shop
                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = "Clothing")
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Clothing"
                    )
                },
                onClick = {

                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = "Tasks")
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Tasks"
                    )
                },
                onClick = {

                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = "Settings")
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                },
                onClick = {

                }
            )
            // TODO: more BottomNavigationItems for each screen in menu
        }
    }
}

@Preview
@Composable
fun NavBarPreview(){
    NavBar()
}