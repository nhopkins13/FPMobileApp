package com.csci448.fpmobileapp.ui.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * Interface to make ScreenSpecs for our app's Screens
 */
sealed interface IScreenSpec {
    val route: String

    @Suppress("ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE")
    @Composable
    fun Content(viewModel: StudySaurusVM, navController: NavController, modifier: Modifier)

    companion object{
        private const val LOG_TAG = "448.IScreenSpec"
        val allScreens = IScreenSpec::class.sealedSubclasses.associate{
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }
        val root = "fpmobileapp"
        val startDestination = StartScreenSpec.route

        @Composable
        fun TopBar(
            viewModel: StudySaurusVM,
            navController: NavHostController,
            navBackStackEntry: NavBackStackEntry?,
            context: Context
        ){
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(
                viewModel = viewModel,
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                context = context
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(
        viewModel: StudySaurusVM,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ){
        TopAppBar(
            navigationIcon = if(navController.previousBackStackEntry != null){
                {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            }else{
                {}
            },
            title = {
                Text(
                    text = stringResource(R.string.app_name)
                )
            },
            actions = {
                TopAppBarActions(
                    viewModel = viewModel,
                    navController = navController,
                    navBackStackEntry = navBackStackEntry,
                    context = context
                )
            }
        )
    }

    @Composable
    fun TopAppBarActions(
        viewModel: StudySaurusVM,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    )
}