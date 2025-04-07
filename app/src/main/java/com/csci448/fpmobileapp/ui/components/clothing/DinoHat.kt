package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform

fun DinoHat(drawScope: DrawScope, size: Size){
    drawScope.withTransform({
        translate(left = 0f, top = 0f)
    }){
        scale(scaleX = 1.0f, scaleY = 0.25f){
            drawCircle(
                color = Color.Black
            )
        }
        scale(scaleX = 0.75f, scaleY = 0.15f){
            drawCircle(color = Color.Red)
        }
        translate(left = 0f, top = -size.height * 0.05f){
            scale(scaleX = 0.75f, scaleY = 0.1f){
                drawRect(color = Color.Red)
            }
        }
        translate(left = 0f, top = -size.height * 0.1f){
            scale(scaleX = 0.75f, scaleY = 0.15f){
                drawCircle(color = Color.Black)
            }
        }
        translate(left = 0f, top = -size.height * 0.15f){
            scale(scaleX = 0.75f, scaleY = 0.1f){
                drawRect(color = Color.Black)
            }
        }
        translate(left = 0f, top = -size.height * 0.2f){
            scale(scaleX = 0.75f, scaleY = 0.15f){
                drawCircle(color = Color.Black)
            }
        }
    }
}