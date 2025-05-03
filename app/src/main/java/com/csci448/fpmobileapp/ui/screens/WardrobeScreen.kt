// File: WardrobeScreen.kt

package com.csci448.fpmobileapp.ui.screens

import android.util.Log // Keep Log import
import androidx.compose.foundation.layout.* // Use wildcard import
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text // Use Material 3 Text
import androidx.compose.runtime.* // Use wildcard import
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.ItemCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun WardrobeScreen(
    viewModel: StudySaurusVM,
    modifier: Modifier = Modifier // Assumes this modifier includes Scaffold padding
) {
    val saurus by viewModel.currentSaurusState
    val ownedItems by viewModel.ownedItems.collectAsState()

    val categories = listOf("Hat", "Neckwear", "Belt")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val selectedCategory = categories[selectedTabIndex]

    val noneHatItem = remember { ShopItem(id = 0, name = "No Hat", type = "Hat", imageId = R.drawable.none, price = 0, owned = true) }
    val noneNeckwearItem = remember { ShopItem(id = 0, name = "No Neckwear", type = "Neckwear", imageId = R.drawable.none, price = 0, owned = true) }
    val noneBeltItem = remember { ShopItem(id = 0, name = "No Belt", type = "Belt", imageId = R.drawable.none, price = 0, owned = true) }

    Column(
        modifier = modifier // Apply Scaffold padding
            .fillMaxSize()
    ) {
        // 1) Dinosaur Canvas Box - Give it MORE weight
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // *** CHANGE HERE: Increase weight for Dino ***
                .weight(2f) // Example: Give Dino 2 parts of the space
            // .padding(bottom = 4.dp) // Optional padding above tabs
            ,
            contentAlignment = Alignment.Center
        ) {
            // DinosaurCanvas will scale up to fit this larger weighted Box
            DinosaurCanvas(
                modifier = Modifier,
                saurus = saurus // Pass the state value directly
            )
        }

        // 2) The TabRow (fixed height, no change)
        TabRow(selectedTabIndex = selectedTabIndex) {
            categories.forEachIndexed { index, title ->
                Tab(
                    selected = (index == selectedTabIndex),
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(text = title, modifier = Modifier.padding(16.dp))
                }
            }
        }

        // 3) The grid of items - Keep its weight, making its proportion smaller
        val itemsForCategory = ownedItems
            .filter { it.type.equals(selectedCategory, ignoreCase = true) }

        val relevantNoneItem = when (selectedCategory) {
            "Hat" -> noneHatItem
            "Neckwear" -> noneNeckwearItem
            "Belt" -> noneBeltItem
            else -> null
        }

        val displayItems = mutableListOf<ShopItem>()
        relevantNoneItem?.let { displayItems.add(it) }
        displayItems.addAll(itemsForCategory)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                // *** CHANGE HERE: Keep weight at 1f (relative to Dino's 2f) ***
                .weight(1f) // Example: Grid gets 1 part of the space
            //.padding(top = 4.dp) // Optional padding below tabs
            ,
            contentPadding = PaddingValues(8.dp) // Padding inside the grid
        ) {
            // Item rendering logic (no changes needed)
            items(displayItems, key = { item -> "${item.type}-${item.id}" }) { item ->
                // ... (Selection logic and ItemCard call remain the same) ...

                val isSelected = when (selectedCategory) {
                    "Hat"      -> saurus.hat      == item.id && item.id != 0
                    "Neckwear" -> saurus.neckWear == item.id && item.id != 0
                    "Belt"     -> saurus.belt     == item.id && item.id != 0
                    else       -> if (item.id == 0) {
                        when (selectedCategory) {
                            "Hat" -> saurus.hat == 0
                            "Neckwear" -> saurus.neckWear == 0
                            "Belt" -> saurus.belt == 0
                            else -> false
                        }
                    } else {
                        false
                    }
                }

                ItemCard(
                    item = item,
                    isSelected = isSelected,
                    showPrice = false,
                    onSelectItem = { selectedItem ->
                        val itemIdToSelect = if (selectedItem.id == 0) 0 else selectedItem.id
                        when (selectedCategory) {
                            "Hat"      -> viewModel.selectHat(itemIdToSelect)
                            "Neckwear" -> viewModel.selectNeckwear(itemIdToSelect)
                            "Belt"     -> viewModel.selectBelt(itemIdToSelect)
                        }
                    }
                )
            }
        }
    }
}