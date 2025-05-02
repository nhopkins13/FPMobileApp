package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.ClothingCard
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.ItemCard
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
fun WardrobeScreen(viewModel: StudySaurusVM) {
    val saurus by viewModel.currentSaurusState
    val ownedItems by viewModel.ownedItems.collectAsState()

    // the three categories must match ShopItem.type exactly:
    val categories = listOf("Hat", "Neckwear", "Belt")

    // UI state for which tab is selected:
    var selectedTabIndex by remember { mutableStateOf(0) }
    val selectedCategory = categories[selectedTabIndex]

    Column {
        DinosaurCanvas(saurus = viewModel.currentSaurusState.value)

        // 2) The TabRow:
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

        // 3) The grid of items for that category:
        val itemsForCategory = ownedItems
            .filter { it.type.equals(selectedCategory, ignoreCase = true) }

        Log.d("DATA_FLOW_DEBUG", "----- Wardrobe Display -----")
        Log.d("DATA_FLOW_DEBUG", "Wardrobe: Selected Tab = $selectedCategory")
        Log.d("DATA_FLOW_DEBUG", "Wardrobe: ownedItems received (${ownedItems.size}) = ${ownedItems.joinToString { it.name + "(id=${it.id}, type=${it.type})" }}")
        Log.d("DATA_FLOW_DEBUG", "Wardrobe: itemsForCategory after filtering (${itemsForCategory.size}) = ${itemsForCategory.joinToString { it.name }}")

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(itemsForCategory) { item ->
                Log.d("DATA_FLOW_DEBUG", "Wardrobe: Displaying ItemCard for ${item.name}")

                val isSelected = when (selectedCategory) {
                    "Hat"      -> saurus.hat      == item.id
                    "Neckwear" -> saurus.neckWear == item.id
                    "Belt"     -> saurus.belt     == item.id
                    else       -> false
                }

                ItemCard(
                    item        = item,
                    isSelected  = isSelected,
                    showPrice = false,
                    onSelectItem = {
                        when (selectedCategory) {
                            "Hat"      -> viewModel.selectHat(it.id)
                            "Neckwear" -> viewModel.selectNeckwear(it.id)
                            "Belt"     -> viewModel.selectBelt(it.id)
                        }
                    }
                )
            }
        }
    }
}
