package com.csci448.fpmobileapp.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.csci448.fpmobileapp.ui.navigation.specs.IScreenSpec
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import kotlinx.coroutines.CoroutineScope

/**
 * The Navhost for the project
 *
 * TODO: implement screens,
 *      make the whole thing,
 *      import kotlin navigation stuff,
 *      make ScreenSpecs for all Screens
 */
@Composable
fun FPMANavHost(navController: NavHostController,
                viewModel: StudySaurusVM,
                modifier: Modifier,
                context: Context,
                coroutineScope: CoroutineScope){
    NavHost(navController = navController, startDestination = IScreenSpec.root){
        navigation(startDestination = IScreenSpec.startDestination, route = IScreenSpec.root){
            IScreenSpec.allScreens.forEach{ (_, screen) ->
                if(screen != null){
                    composable(route = screen.route){
                        screen.Content(viewModel, navController, modifier)
                    }
                }
            }
        }
    }
}