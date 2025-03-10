package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.screens.LoginScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object LoginScreenSpec : IScreenSpec {
    override val route: String
        get() = "LoginScreen"

    @Composable
    override fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier) {
        LoginScreen(
            viewModel = viewModel,
            goToHome = {
                viewModel.setCurrentScreen(SelectedScreen.HOME)
                navController.navigate(route = HomeScreenSpec.route)
            },
            modifier = modifier
        )
    }
}