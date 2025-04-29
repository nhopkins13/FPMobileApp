package com.csci448.fpmobileapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.data.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.data.TaskDao
import com.csci448.fpmobileapp.ui.components.clothing.DinoHat
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy.None

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

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun ClothingCardPreview(){
    val testItem = ShopItem(id=0, name="None", type="hat", imageId=0, price=0)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        ClothingCard(item=testItem, isSelected = false, onSelectItem={})
    }
}