package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * Interface to make ScreenSpecs for our app's Screens
 *
 * TODO:
 *  implement everything, 
 *  add val route: String,
 *  add content(viewmodel, navcontroller, modifier)
 */
sealed interface IScreenSpec {
    val route: String

    @Suppress("ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE")
    @Composable
    fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier)

    companion object{
        val allScreens = IScreenSpec::class.sealedSubclasses.map{ it.objectInstance }
        val root = "fpmobileapp"
        val startDestination = StartScreenSpec.route
    }
}