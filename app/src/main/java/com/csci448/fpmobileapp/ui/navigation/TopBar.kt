package com.csci448.fpmobileapp.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.fpmobileapp.ui.navigation.specs.IScreenSpec
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun TopBar(
    viewModel: StudySaurusVM,
    navController: NavHostController,
    context: Context
){
    val navBackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        viewModel = viewModel,
        navController = navController,
        context = context,
        navBackStackEntry = navBackEntryState.value,
        screenName = stringResource(viewModel.currentScreen.value.stringRes)
    )
}