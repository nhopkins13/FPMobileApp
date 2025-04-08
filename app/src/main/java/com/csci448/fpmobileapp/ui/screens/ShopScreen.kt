package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.ItemRepo
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.ClothingCard
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
fun ShopScreen(viewModel: StudySaurusVM, modifier: Modifier = Modifier) {
    // 1. Observe unowned items from VM
    val clothingItems by viewModel.shopItems.collectAsState()

    // 2. Observe coins
    val coins by viewModel.totalCoins.collectAsState()

    // 3. The user’s selected items
    val selectedItems = remember { mutableStateListOf<ShopItem>() }

    // 4. Dialog state
    val showInsufficientCoinsDialog = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        // COIN DISPLAY
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Coins: $coins",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        // If no items in DB, show "no items"
        if (clothingItems.isEmpty()) {
            Text(text = stringResource(R.string.shop_empty), modifier = Modifier.padding(16.dp))
        } else {
            // Scrollable list
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(clothingItems) { item ->
                    ClothingCard(
                        item = item,
                        viewModel = viewModel,
                        onSelectItem = { clickedItem ->
                            // Toggle selection
                            if (selectedItems.contains(clickedItem)) {
                                selectedItems.remove(clickedItem)
                            } else {
                                selectedItems.add(clickedItem)
                            }
                        }
                    )
                }
            }

            // PURCHASE BUTTON
            androidx.compose.material3.Button(
                onClick = {
                    val totalCost = selectedItems.sumOf { it.price }
                    if (coins < totalCost) {
                        // Not enough coins
                        showInsufficientCoinsDialog.value = true
                    } else {
                        // Enough "coins" => mark them owned in DB
                        viewModel.purchaseItems(selectedItems)
                        // Clear selected
                        selectedItems.clear()
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                androidx.compose.material3.Text(stringResource(R.string.shop_purchase))
            }
        }
    }

    // AlertDialog for insufficient coins
    if (showInsufficientCoinsDialog.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showInsufficientCoinsDialog.value = false },
            title = { Text("Not Enough Coins") },
            text = {
                Text("Looks like you don't have enough coins. Complete more tasks to collect more coins!")
            },
            confirmButton = {
                androidx.compose.material3.Button(
                    onClick = { showInsufficientCoinsDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }
}
