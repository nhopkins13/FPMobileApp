package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform

fun DinoPearls(drawScope: DrawScope, size: Size){
    drawScope.withTransform({

    }){
        scale(scaleX = 0.025f, scaleY = 0.025f){
            for(i in 0..8){
                translate(left = size.width * (-4 + i)){
                    drawCircle(color = Color(red = 0.9f, green = 0.9f, blue = 0.8f))
                    drawCircle(color = Color(red = 0.5f, green = 0.5f, blue = 0.5f), style = Stroke(width = 50f))
                }
            }
        }
    }
}