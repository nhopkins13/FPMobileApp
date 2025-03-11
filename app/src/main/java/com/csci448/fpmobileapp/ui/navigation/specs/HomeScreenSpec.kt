package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
}