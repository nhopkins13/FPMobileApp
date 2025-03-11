package com.csci448.fpmobileapp.ui.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.screens.HomeScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object HomeScreenSpec : IScreenSpec {
    override val route: String
        get() = "HomeScreen"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        viewModel.setCurrentScreen(SelectedScreen.HOME)
        HomeScreen(viewModel = viewModel,
            {navController.navigate(route = SettingScreenSpec.route)},
            {navController.navigate(route = WardrobeScreenSpec.route)},
            {navController.navigate(route = ShopScreenSpec.route)},
            {navController.navigate(route = TaskScreenSpec.route)},
            {navController.navigate(route = SocialScreenSpec.route)},
            modifier = modifier)
    }

    @Composable
    override fun TopAppBarActions(
        viewModel: StudySaurusVM,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        IconButton(
            onClick = {
                navController.navigate(SettingScreenSpec.route)
            }
        ){
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.label_settings)
            )
        }
    }
}