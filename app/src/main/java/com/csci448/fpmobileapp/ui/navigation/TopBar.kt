package com.csci448.fpmobileapp.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val screenName: MutableState<String?> = remember {mutableStateOf(null)}
    if (viewModel.currentScreen.value.stringRes != null){
        screenName.value = stringResource(viewModel.currentScreen.value.stringRes!!)
    }
    IScreenSpec.TopBar(
        viewModel = viewModel,
        navController = navController,
        context = context,
        navBackStackEntry = navBackEntryState.value,
        screenName = screenName.value
    )
}