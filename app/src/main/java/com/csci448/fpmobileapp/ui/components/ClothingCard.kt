package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.clothing.DinoHat

@Composable
fun ClothingCard(
    item: ShopItem,
    isSelected: Boolean,
    onSelectItem: (ShopItem)->Unit // <-- new callback
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.5f),
        onClick = {
            // Set the dinosaur's hat in the VM
            //viewModel.currentSaurusState.value.hat = item.id
            // Also notify "we selected this item" for purchase
            onSelectItem(item)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            // 1) The actual image of the item:
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 2) The name (and if you want, price here or leave it in ShopScreen)
            Text(text = item.name)

            // 3) (Optional) Show selection state:
            if (isSelected) {
                Text(text = "✓", modifier = Modifier.padding(top = 4.dp))
            }
        }

        Text(text = item.name)

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            if (item.id == 1) {
                withTransform({
                    translate(left = 0.0f, top = -size.height * 0.25f)
                }) {
                    DinoHat(this, size)
                }
            } else if (item.id == 0) {
                drawCircle(color = Color.Gray)
                scale(scaleX = 0.75f, scaleY = 0.75f) {
                    drawCircle(color = Color.White)
                }
                rotate(degrees = 45f) {
                    scale(scaleX = 0.9f, scaleY = 0.1f) {
                        drawRect(color = Color.Gray)
                    }
                }
            }
        }
    }
}