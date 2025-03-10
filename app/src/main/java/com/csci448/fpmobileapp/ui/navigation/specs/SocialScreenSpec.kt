package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.ui.screens.ShopScreen
import com.csci448.fpmobileapp.ui.screens.SocialScreen
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

object SocialScreenSpec : IScreenSpec {
    override val route: String
        get() = "SocialScreen"

    @Composable
    override fun Content(
        viewModel: StudySaurusVM,
        navController: NavController,
        modifier: Modifier
    ) {
        SocialScreen(viewModel = viewModel, modifier = modifier)
    }
}