package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.ClothingCard
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
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
fun WardrobeScreen(viewModel : StudySaurusVM, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        // Dino canvas
        DinosaurCanvas(viewModel = viewModel)

        Box(modifier = Modifier) {
            // Card 1
            ClothingCard(
                item = ShopItem(
                    id = 0,
                    name = "None",
                    type = "Hat",
                    imageId = 0,
                    owned = true,
                    price = 5
                ),
                onSelectItem = { selectedItem ->
                    viewModel.currentSaurusState.value.hat = selectedItem.id
                }
            )

            // Card 2
            ClothingCard(
                item = ShopItem(
                    id = 1,
                    name = "Top Hat",
                    type = "Hat",
                    imageId = 1,
                    owned = true,
                    price = 10
                ),
                onSelectItem = { selectedItem ->
                    viewModel.currentSaurusState.value.hat = selectedItem.id
                }
            )
        }
    }
}
