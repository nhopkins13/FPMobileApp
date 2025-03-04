package com.csci448.fpmobileapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun NavBar(studySaurusVM: StudySaurusVM,
           navController: NavHostController){
    // TODO: pass in view model with status on which icon is selected
    BottomAppBar {
        BottomNavigation {
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(R.string.label_home))
                },
                selected = false, // TODO: set status on which screen is active
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.label_home)
                    )
                },
                onClick = {
                    // TODO: navigate to screen represented by button
                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(R.string.label_shop))
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = stringResource(R.string.label_shop)
                    )
                },
                onClick = {
                    // TODO: navigate to shop
                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(R.string.label_wardrobe))
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(R.string.label_wardrobe)
                    )
                },
                onClick = {

                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(R.string.label_tasks))
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = stringResource(R.string.label_tasks)
                    )
                },
                onClick = {

                }
            )
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(R.string.label_settings))
                },
                selected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.label_settings)
                    )
                },
                onClick = {

                }
            )

        }
    }
}

@Preview
@Composable
fun NavBarPreview(){
    //NavBar()
}