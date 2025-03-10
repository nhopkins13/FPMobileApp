package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.ui.screens.NewTaskScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object NewTaskScreenSpec: IScreenSpec {
    override val route = "new_task"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        NewTaskScreen(viewModel = viewModel, modifier = modifier)
    }
}