package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.screens.StartupScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object StartScreenSpec : IScreenSpec {
    override val route: String
        get() = "StartupScreen"

    @Composable
    override fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier) {
        viewModel.setCurrentScreen(SelectedScreen.STARTUP)
        StartupScreen(
            viewModel = viewModel,
            goToHome = {navController.navigate(route = HomeScreenSpec.route)},
            goToLogin = {
                navController.navigate(route = LoginScreenSpec.route)},
            goToSignup = {
                navController.navigate(route = SignupScreenSpec.route)},
            modifier = modifier)
    }
}