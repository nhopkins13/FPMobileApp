package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.ui.components.clothing.DinoHat
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun ClothingCard(
    item: ShopItem,
    viewModel: StudySaurusVM,
    onSelectItem: (ShopItem) -> Unit // <-- new callback
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(0.5f),
        onClick = {
            // Set the dinosaur's hat in the VM
            viewModel.currentSaurusState.value.hat = item.id
            // Also notify "we selected this item" for purchase
            onSelectItem(item)
        }
    ) {
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
                    scale(scaleX = 0f, scaleY = 0.25f) {
                        drawRect(color = Color.Gray)
                    }
                }
            }
        }
    }
}
