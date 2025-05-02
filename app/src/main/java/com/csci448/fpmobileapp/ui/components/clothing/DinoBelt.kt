package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.drawscope.translate


fun DinoBelt(drawScope: DrawScope, size: Size){
    drawScope.withTransform({
        translate(left=0f, top=0f)
    }){
        scale(scaleX = 0.35f, scaleY = 0.05f * size.width / size.height){
            drawRect(color = Color.Black)
            translate(left = -size.width * 0.45f){
                scale(scaleX = 0.1f, scaleY = 1.1f){
                    drawRect(color = Color.Yellow, style = Stroke(width=300f))
                }
            }
        }
    }
}