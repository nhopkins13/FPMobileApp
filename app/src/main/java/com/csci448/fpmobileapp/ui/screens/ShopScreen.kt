package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.ItemRepo
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.ItemCard
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * the app's shop
 * will contain items & accessories for the user's saurus
 * uses in-game currency earned via tasks
 *
 * TODO:
 *  reorganize,
 *  implement item database instead of herd-coded test object
 */
@Composable
fun ShopScreen(viewModel : StudySaurusVM, modifier: Modifier = Modifier){
    /*Column(modifier = modifier) {
        Text("SHOP SCREEN")
        Text("nothing here")
        Text("hit back button in studio")
    }*/
    val items = listOf( ItemRepo.testItem)

    Column(modifier = modifier.fillMaxSize()) {
        if (items.isEmpty()) {
            androidx.compose.material3.Text(text = stringResource(R.string.no_tasks_label))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(items) { shopitem ->
                    ItemCard(shopItem = shopitem)
                }
            }

            androidx.compose.material3.Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
            ) {
                androidx.compose.material3.Text(stringResource(R.string.shop_purchase))
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewShopScreen(){
}