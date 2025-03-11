package com.csci448.fpmobileapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.components.NavBar
import com.csci448.fpmobileapp.ui.navigation.FPMANavHost
import com.csci448.fpmobileapp.ui.navigation.TopBar
import com.csci448.fpmobileapp.ui.theme.FPMobileAppTheme
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * The main function that runs the app
 *
 * TODO:
 *  place (temporarily) main screen into scaffold,
 *  replace scaffold content with NavHost,
 *  create viewmodel & viewmodel factory,
 *  override functions for console logs
 */
val viewModel = StudySaurusVM(SaurusRepo.mySaurus)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()

            val visibleScreens = listOf(
                SelectedScreen.HOME,
                SelectedScreen.SHOP,
                SelectedScreen.TASKS,
                SelectedScreen.WARDROBE,
                SelectedScreen.SOCIAL
            )

            Scaffold(
                bottomBar = {
                    if (viewModel.currentScreen.value in visibleScreens) {
                        NavBar(viewModel, navController)
                    }
                },
                topBar = {
                    TopBar(
                        viewModel = viewModel,
                        navController = navController,
                        context = LocalContext.current
                    )
                }
            ) { innerPadding ->
                FPMANavHost(
                    navController = navController,
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    context = LocalContext.current,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello testing!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FPMobileAppTheme {
        Greeting("Android")
    }
}