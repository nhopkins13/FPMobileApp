package com.csci448.fpmobileapp.ui.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings // Example icon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.screens.SettingScreen // Import your SettingScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object SettingScreenSpec : IScreenSpec {
    override val route: String = "SettingScreen"

    @Composable
    override fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier) {
        viewModel.setCurrentScreen(SelectedScreen.SETTINGS) // Update VM state if needed
        SettingScreen(
            viewModel = viewModel,
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
        // No specific actions needed for settings by default
    }
}