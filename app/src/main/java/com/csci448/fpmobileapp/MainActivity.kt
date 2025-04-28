package com.csci448.fpmobileapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.csci448.fpmobileapp.data.AppDatabase
import com.csci448.fpmobileapp.data.ItemRepo
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.components.NavBar
import com.csci448.fpmobileapp.ui.navigation.FPMANavHost
import com.csci448.fpmobileapp.ui.navigation.TopBar
import com.csci448.fpmobileapp.ui.theme.FPMobileAppTheme
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVMFactory

/**
 * The main function that runs the app
 *
 * TODO:
 *  place (temporarily) main screen into scaffold,
 *  replace scaffold content with NavHost,
 *  create viewmodel & viewmodel factory,
 *  override functions for console logs
 */
class MainActivity : ComponentActivity() {
    companion object{
        private val LOG_TAG = "448.FPMobileApp.MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current.applicationContext

            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "my_app_db"
            )
                .fallbackToDestructiveMigration() // allow wiping old data
                .build()


            val factory = StudySaurusVMFactory(
                mySaurus = SaurusRepo.mySaurus,
                taskDao = db.taskDao(),
                itemsDao = db.itemsDao() // <--- Must pass itemsDao here
            )

            val viewModel: StudySaurusVM = androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

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
                LaunchedEffect(Unit) {
                    var allItem = viewModel.allItems
                    var shopItem = viewModel.allItems
                    Log.d(LOG_TAG, "all items $allItem")
                    Log.d(LOG_TAG, "shop items $shopItem")
                    if (!viewModel.allItems.value.contains(ItemRepo.testHat)) {
                        Log.d(LOG_TAG, "inserting testhat")
                        viewModel.insertItem(item = ItemRepo.testHat)
                    }
                    allItem = viewModel.allItems
                    shopItem = viewModel.allItems
                    Log.d(LOG_TAG, "all items $allItem")
                    Log.d(LOG_TAG, "shop items $shopItem")
                }
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