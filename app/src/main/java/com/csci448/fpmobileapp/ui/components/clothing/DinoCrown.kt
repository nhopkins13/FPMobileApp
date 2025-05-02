package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform

fun DinoCrown(drawScope: DrawScope, size: Size){
    drawScope.withTransform({

    }){
        scale(scaleX = 0.9f, scaleY = 0.15f){
            drawRect(color = Color(red = 0.75f, green = 0.65f, blue = 0.0f))
        }
        translate(left = - size.width * 0.275f, top = - size.height * 0.075f) {
            rotate(degrees = 45f){
                scale(scaleX = 0.25f, scaleY = 0.25f * size.width / size.height) {
                    drawRect(color = Color(red = 0.75f, green = 0.65f, blue = 0.0f))
                }
            }
            scale(scaleX = 0.1f, scaleY = 0.1f){
                drawCircle(color = Color(red = 0.75f, green = 0.1f, blue = 0.1f))
            }
        }
        translate(top = - size.height * 0.075f) {
            rotate(degrees = 45f){
                scale(scaleX = 0.25f, scaleY = 0.25f * size.width / size.height) {
                    drawRect(color = Color(red = 0.75f, green = 0.65f, blue = 0.0f))
                }
            }
            scale(scaleX = 0.1f, scaleY = 0.1f){
                drawCircle(color = Color(red = 0.75f, green = 0.1f, blue = 0.1f))
            }
        }
        translate(left = size.width * 0.275f, top = - size.height * 0.075f) {
            rotate(degrees = 45f){
                scale(scaleX = 0.25f, scaleY = 0.25f * size.width / size.height) {
                    drawRect(color = Color(red = 0.75f, green = 0.65f, blue = 0.0f))
                }
            }
            scale(scaleX = 0.1f, scaleY = 0.1f){
                drawCircle(color = Color(red = 0.75f, green = 0.1f, blue = 0.1f))
            }
        }
    }
}