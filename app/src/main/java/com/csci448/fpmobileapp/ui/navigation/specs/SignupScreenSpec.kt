package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.ui.screens.SignupScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object SignupScreenSpec : IScreenSpec {
    override val route: String
        get() = "SignupScreen"

    @Composable
    override fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier) {
        SignupScreen(viewModel = viewModel, goToHome = {})
    }
}