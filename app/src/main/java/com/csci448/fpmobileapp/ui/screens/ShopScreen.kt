package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * the app's shop
 * will contain items & accessories for the user's saurus
 * uses in-game currency earned via tasks
 *
 * TODO:
 *  reorganize,
 *  actually implement shop
 */
@Composable
fun ShopScreen(viewModel : StudySaurusVM, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text("SHOP SCREEN")
        Text("nothing here")
        Text("hit back button in studio")
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewShopScreen(){
}