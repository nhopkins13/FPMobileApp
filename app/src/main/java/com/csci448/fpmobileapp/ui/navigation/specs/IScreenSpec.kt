package com.csci448.fpmobileapp.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

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

    @Composable
    fun Content(navController: NavController, modifier: Modifier){

    }

    companion object{
        val allScreens = IScreenSpec::class.sealedSubclasses.map{ it.objectInstance }
        val root = "TODO? app name?"
        val startDestination = "startup"
    }
}