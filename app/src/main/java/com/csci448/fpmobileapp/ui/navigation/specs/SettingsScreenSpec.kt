package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
        SettingScreen(viewModel = viewModel, modifier = modifier)
    }
}