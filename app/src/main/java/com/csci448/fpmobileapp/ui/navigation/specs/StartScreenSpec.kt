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

    @Composable
    override fun TopAppBarActions(
        viewModel: StudySaurusVM,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }
}