package com.csci448.fpmobileapp.ui.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
        viewModel.setCurrentScreen(SelectedScreen.TASKS)
        TaskScreen(viewModel = viewModel, modifier = modifier)
    }

    @Composable
    override fun TopAppBarActions(
        viewModel: StudySaurusVM,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        IconButton(
            onClick = {
                navController.navigate(NewTaskScreenSpec.route)
            }
        ){
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = stringResource(R.string.label_new_task)
            )
        }
    }
}