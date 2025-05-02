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

    /* ---- step 1: runtime check, **no composables here** ------------------- */
    val showImage = remember(item.imageId) {
        if (item.imageId == 0) {
            false                               // “no image” sentinel
        } else {
            /* Detect bad / unexpected resource types safely */
            runCatching {
                val type = ctx.resources.getResourceTypeName(item.imageId)
                // painterResource is happy with "drawable" and "mipmap"
                type == "drawable" || type == "mipmap"
            }.getOrDefault(false)
        }
    }
    /* ---------------------------------------------------------------------- */

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

            /* ---- step 2: now it’s safe to call the composable -------------- */
            if (showImage) {
                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = item.name,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.height(4.dp))
            }
            /* ---------------------------------------------------------------- */

            Text(item.name, maxLines = 1)

            if (showPrice)  Text("$${item.price}", fontSize = 12.sp)
            if (isSelected) Text("✓", modifier = Modifier.padding(top = 2.dp))
        }
    }
}
