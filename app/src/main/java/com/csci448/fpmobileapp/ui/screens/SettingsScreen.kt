package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * controls the app settings
 *
 * TODO:
 *  reorganize,
 *  actually implement settings
 */
@Composable
fun SettingScreen(viewModel : StudySaurusVM, modifier: Modifier = Modifier){
Column(modifier = modifier) {
    //remove/change once page has content
    Text("SETTINGS SCREEN")
    Text("nothing here")
    Text("hit back button in studio")
}
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewSettingScreen(){
}