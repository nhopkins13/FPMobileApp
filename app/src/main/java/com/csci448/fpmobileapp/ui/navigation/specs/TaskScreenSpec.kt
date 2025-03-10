package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.ui.screens.TaskScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object TaskScreenSpec : IScreenSpec {
    override val route: String
        get() = "TaskScreen"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        TaskScreen(viewModel = viewModel, modifier = modifier)
    }
}