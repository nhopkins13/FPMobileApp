package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * Allows the user to change the appearance of their saurus
 * includes accessories owned by the user
 *
 * TODO:
 *  reorganize,
 *  actually implement wardrobe & saurus customization
 */
@Composable
fun WardrobeScreen(viewModel : StudySaurusVM){
    Column {
        //remove/change once page has content
        Text("WARDROBE SCREEN")
        Text("nothing here")
        Text("hit back button in studio")
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewWardrobeScreen(){
    WardrobeScreen(viewModel = StudySaurusVM(SaurusRepo.mySaurus))
}