package com.csci448.fpmobileapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.navigation.specs.HomeScreenSpec
import com.csci448.fpmobileapp.ui.navigation.specs.ShopScreenSpec
import com.csci448.fpmobileapp.ui.navigation.specs.SocialScreenSpec
import com.csci448.fpmobileapp.ui.navigation.specs.TaskScreenSpec
import com.csci448.fpmobileapp.ui.navigation.specs.WardrobeScreenSpec
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun NavBar(studySaurusVM: StudySaurusVM,
           navController: NavHostController){
    val visibleScreens = listOf(
        SelectedScreen.HOME,
        SelectedScreen.SHOP,
        SelectedScreen.WARDROBE,
        SelectedScreen.TASKS,
        SelectedScreen.SOCIAL
    )
    if (studySaurusVM.currentScreen.value in visibleScreens) {
        BottomAppBar (containerColor = Color(0.5f,0.6f,0.8f)){
            BottomNavigation {
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(R.string.label_home), fontSize = 14.sp)
                    },
                    selected = studySaurusVM.currentScreen.value == SelectedScreen.HOME,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = stringResource(R.string.label_home)
                        )
                    },
                    onClick = {
                        navController.navigate(HomeScreenSpec.route)
                    },
                    unselectedContentColor = Color(0f, 0f, 0f),
                    selectedContentColor = Color(0.2f, 0.25f, 0.5f)
                )
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(R.string.label_shop), fontSize = 14.sp)
                    },
                    selected = studySaurusVM.currentScreen.value == SelectedScreen.SHOP,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = stringResource(R.string.label_shop)
                        )
                    },
                    onClick = {
                        navController.navigate(ShopScreenSpec.route)
                    },
                    unselectedContentColor = Color(0f, 0f, 0f),
                    selectedContentColor = Color(0.2f, 0.25f, 0.5f)
                )
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(R.string.label_wardrobe), fontSize = 12.sp)
                    },
                    selected = studySaurusVM.currentScreen.value == SelectedScreen.WARDROBE,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(R.string.label_wardrobe)
                        )
                    },
                    onClick = {
                        navController.navigate(WardrobeScreenSpec.route)
                    },
                    unselectedContentColor = Color(0f, 0f, 0f),
                    selectedContentColor = Color(0.2f, 0.25f, 0.5f)
                )
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(R.string.label_tasks), fontSize = 14.sp)
                    },
                    selected = studySaurusVM.currentScreen.value == SelectedScreen.TASKS,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = stringResource(R.string.label_tasks)
                        )
                    },
                    onClick = {
                        navController.navigate(TaskScreenSpec.route)
                    },
                    unselectedContentColor = Color(0f, 0f, 0f),
                    selectedContentColor = Color(0.2f, 0.25f, 0.5f)
                )
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(R.string.label_social), fontSize = 14.sp)
                    },
                    selected = studySaurusVM.currentScreen.value == SelectedScreen.SOCIAL,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.label_social)
                        )
                    },
                    onClick = {
                        navController.navigate(SocialScreenSpec.route)
                    },
                    unselectedContentColor = Color(0f, 0f, 0f),
                    selectedContentColor = Color(0.2f, 0.25f, 0.5f)
                )

            }
        }
    }
}

@Preview
@Composable
fun NavBarPreview(){
    //NavBar()
}