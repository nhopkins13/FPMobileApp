package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
// In ShopScreen.kt

@Composable
fun ShopScreen(
    viewModel: StudySaurusVM,
    modifier: Modifier = Modifier
) {
    // 1) Observe unowned items:
    val clothingItems = remember { listOf(ItemRepo.topHat) }
    val shopItems by viewModel.shopItems.collectAsState()

    // 2) Observe available coins  (earned – spent):
    val coins by viewModel.availableCoins.collectAsState()

    // 3) UI state for category tabs:
    val categories = listOf("Hat", "Neckwear", "Belt")
    val tabState = remember { mutableStateOf(0) }
    val selectedTabIndex = tabState.value
    val selectedCategory = categories[selectedTabIndex]

    // 4) Local selection buffer:
    val selectedItems = remember { mutableStateListOf<ShopItem>() }
    val showTooFew = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        // ─── Coin display ───────────────────────────────
        Text(
            text = "Coins: $coins",
            modifier = Modifier.padding(16.dp)
        )

        // ─── Tabs ───────────────────────────────────────
        TabRow(selectedTabIndex = selectedTabIndex) {
            categories.forEachIndexed { idx, title ->
                Tab(
                    selected = idx == selectedTabIndex,
                    onClick = { tabState.value = idx }

                ) {
                    Text(title, modifier = Modifier.padding(16.dp))
                }
            }
        }

        // ─── Filter & grid ─────────────────────────────
        val itemsForCat = shopItems.filter {
            it.type.equals(selectedCategory, ignoreCase = true)
        }

        LazyVerticalGrid(
            columns        = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier       = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(itemsForCat) { item -> // 'item' comes from viewModel.shopItems via filtering
                // Log the item details *before* trying to display it
                Log.d("ITEM_RENDER_DEBUG", "Attempting to render ItemCard for: ${item.name} (id=${item.id}, imageId=${item.imageId})")

                ItemCard( // This call might crash if item.imageId is bad
                    item        = item,
                    isSelected  = selectedItems.contains(item),
                    onSelectItem = { itemFromGrid ->
                        Log.d("SHOP_SELECT_DEBUG", "ItemCard clicked for: ${itemFromGrid.name} (id=${itemFromGrid.id})") // Keep previous log
                        if (selectedItems.contains(itemFromGrid)) {
                            Log.d("SHOP_SELECT_DEBUG", "Removing from selection.") // Keep previous log
                            selectedItems.remove(itemFromGrid)
                        } else {
                            Log.d("SHOP_SELECT_DEBUG", "Adding to selection: ${itemFromGrid.name} (id=${itemFromGrid.id})") // Keep previous log
                            selectedItems.add(itemFromGrid)
                            Log.d("SHOP_SELECT_DEBUG", "Item added. Current selection IDs: ${selectedItems.joinToString { it.id.toString() }}") // Keep previous log
                        }
                    }
                )
            }
        }

        // ─── Purchase button ───────────────────────────
        val totalCost = selectedItems.sumOf { it.price }
        Button(
            onClick = {
                if (coins < totalCost.toLong()) { // Convert totalCost to Long for comparison
                    showTooFew.value = true
                } else {
                    viewModel.purchaseItems(selectedItems.toList())
                    selectedItems.clear()
                }
            },
        ) {
            androidx.compose.material3.Text("Purchase ($$totalCost)") // Display Int cost
        }
    }

    // ─── “not enough coins” dialog ─────────────────
    if (showTooFew.value) {
        AlertDialog(
            onDismissRequest = { showTooFew.value = false },
            title            = { Text("Not enough coins") },
            text             = { Text("Earn more by completing tasks.") },
            confirmButton    = {
                Button(onClick = { showTooFew.value = false }) {
                    Text("OK")
                }
            }
        )
    }
}
