package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.csci448.fpmobileapp.data.ShopItem

@Composable
fun ItemCard(
    shopItem: ShopItem,
    viewModel: ViewModel,
    onCheckedChange: (ShopItem) -> Unit = {},
    onPurchase: ()-> Unit = {}
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){

        var isChecked by remember { mutableStateOf(shopItem.owned) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onCheckedChange(shopItem)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = shopItem.name,
                    fontSize = 20.sp
                )
                Text(
                    text = "Due: ${shopItem.type}"
                )
            }

            Text(
                text = "$${shopItem.price}",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}
