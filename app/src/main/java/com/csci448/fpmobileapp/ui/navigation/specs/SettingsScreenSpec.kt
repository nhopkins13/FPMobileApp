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
import com.csci448.fpmobileapp.ui.screens.SettingScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object SettingScreenSpec : IScreenSpec {
    override val route: String
        get() = "SettingScreen"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        viewModel.setCurrentScreen(SelectedScreen.SETTINGS)

        SettingScreen(
            viewModel = viewModel,
            onSignOut = {
                navController.navigate(StartScreenSpec.route) {
                    // clear all the way back to the start
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    // avoid multiple copies
                    launchSingleTop = true
                }
            },
            modifier = modifier
        )

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