package com.csci448.fpmobileapp.ui.components

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.fpmobileapp.data.ShopItem

@Composable
fun ItemCard(
    item: ShopItem,
    isSelected: Boolean,
    showPrice: Boolean = true,
    onSelectItem: (ShopItem) -> Unit
) {
    val ctx = LocalContext.current
    LaunchedEffect(item.imageId) {
        try {
            val name = ctx.resources.getResourceEntryName(item.imageId)
            val type = ctx.resources.getResourceTypeName(item.imageId)
            Log.d("ItemCard", "Attempting to load imageId=$item.imageId → $type/$name")
        } catch (e: Resources.NotFoundException) {
            Log.e("ItemCard", "Bad resource ID: ${item.imageId}")
        }
    }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .width(100.dp),
        onClick = { onSelectItem(item) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            // Only load the PNG if imageId is nonzero (and thus hopefully valid)
            if (item.imageId != 0) {
                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = item.name,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.height(4.dp))
            }

            // Always show name
            Text(text = item.name, maxLines = 1)

            // And price, if requested
            if (showPrice) {
                Text(text = "$${item.price}", fontSize = 12.sp)
            }

            // Selection marker
            if (isSelected) {
                Text("✓", modifier = Modifier.padding(top = 2.dp))
            }
        }
    }
}

