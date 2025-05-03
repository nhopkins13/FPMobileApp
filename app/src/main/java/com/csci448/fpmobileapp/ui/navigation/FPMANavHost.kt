package com.csci448.fpmobileapp.ui.navigation

import android.content.Context
import android.util.Log // Import Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation // Keep this if using nested navigation graphs
import com.csci448.fpmobileapp.ui.navigation.specs.* // Import all specs
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import kotlinx.coroutines.CoroutineScope

@Composable
fun FPMANavHost(
    navController: NavHostController,
    viewModel: StudySaurusVM,
    modifier: Modifier,
    context: Context,
    coroutineScope: CoroutineScope
) {
    // Collect the current user state to determine the start destination
    // Note: Using .value here assumes the VM initializes currentUser quickly in its init block.
    // A more robust solution might involve showing a loading screen while checking auth state.
    val currentUser by viewModel.currentUser.collectAsState() // Collect state

    val startDestination = StartScreenSpec.route

    Log.d("FPMANavHost", "Building NavHost with startDestination: $startDestination")

    // Use the dynamically determined startDestination
    NavHost(
        navController = navController,
        // Use the calculated start destination
        startDestination = startDestination,
        modifier = modifier
        // Removed the nested navigation(route = IScreenSpec.root) for simplicity,
        // unless you specifically need a nested graph with that root.
        // If you keep it, ensure startDestination logic works with it.
    ) {
        // Register all screens defined in IScreenSpec companion object
        IScreenSpec.allScreens.values.forEach { screen ->
            if (screen != null) {
                composable(route = screen.route) { backStackEntry -> // Pass backStackEntry
                    Log.d("FPMANavHost", "Composing route: ${screen.route}")
                    // Pass arguments if needed in the future using backStackEntry.arguments
                    screen.Content(viewModel, navController, modifier)
                }
            }
        }
    }
}