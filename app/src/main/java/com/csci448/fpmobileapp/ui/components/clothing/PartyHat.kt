package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform

fun PartyHat(drawScope: DrawScope, size: Size){
    drawScope.withTransform({

    }){
        val hatPath = Path()
        hatPath.moveTo(size.width * 0.25f, size.height * 0.46f)
        hatPath.lineTo(size.width * 0.5f, size.height * 0.2f)
        hatPath.lineTo(size.width * 0.75f, size.height * 0.46f)

        drawPath(hatPath, color = Color(red = 0.0f, green = 0.5f, blue = 1.0f))

        translate(top = -size.height * 0.3f) {
            for( i in 0..4){
                rotate(i * 45f){
                    scale(scaleX = 0.025f, scaleY = 0.15f * size.width / size.height) {
                        drawRect(color = Color(red = 1.0f, green = 0.5f, blue = 0.5f))
                    }
                }
            }

        }
    }
}