package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.NavButton
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * The home screen of the app.
 * Displays the studysaurus, has notifications & related, as buttons to access other parts of app
 *
 * TODO:
 *  reorganize,
 *  add  more visuals
 */
@Composable
fun HomeScreen(
    viewModel : StudySaurusVM,
    toSettings: () -> Unit = {},
    toWardrobe: () -> Unit = {},
    toShop: () -> Unit = {},
    toTasks: () -> Unit = {},
    toSocial: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        //remove/change once page has content
        Text("HOME SCREEN")
        NavButton ("Settings", toSettings)
        NavButton ("Wardrobe", toWardrobe)
        NavButton ("Shop", toShop)
        NavButton ("Tasks", toTasks)
        NavButton ("Social", toSocial)
        NavButton ("Crash"){
            throw RuntimeException("Test Crash")
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewHomeScreen(){
    HomeScreen(viewModel = StudySaurusVM(SaurusRepo.mySaurus))
}