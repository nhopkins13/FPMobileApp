package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.screens.WardrobeScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object WardrobeScreenSpec : IScreenSpec {
    override val route: String
        get() = "WardrobeScreen"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        viewModel.setCurrentScreen(SelectedScreen.WARDROBE)
        WardrobeScreen(viewModel = viewModel, modifier = modifier)
    }
}