package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform

fun Bowtie(drawScope: DrawScope, size: Size){
    drawScope.withTransform({}){
        scale(scaleX = 0.2f, scaleY = 0.0125f){
            drawRect(color = Color.Red)
        }

        val bowPath = Path()

        bowPath.moveTo(size.width * 0.45f, size.height * 0.48f)
        bowPath.lineTo(size.width * 0.45f, size.height * 0.52f)
        bowPath.lineTo(size.width * 0.35f, size.height * 0.48f)
        bowPath.lineTo(size.width * 0.35f, size.height * 0.52f)

        drawPath(bowPath, Color.Red)

        val bowOutline = Path()

        bowOutline.moveTo(size.width * 0.45f, size.height * 0.48f)
        bowOutline.lineTo(size.width * 0.45f, size.height * 0.52f)
        bowOutline.lineTo(size.width * 0.35f, size.height * 0.48f)
        bowOutline.lineTo(size.width * 0.35f, size.height * 0.52f)
        bowOutline.lineTo(size.width * 0.45f, size.height * 0.48f)

        drawPath(bowOutline, Color(red = 0.5f, green = 0.0f, blue = 0.0f), style = Stroke(width = 3f))

        translate(left = -size.width * 0.1f) {
            scale(scaleX = 0.02f, scaleY = 0.02f){
                drawCircle(color = Color.Red)
                drawCircle(color = Color(red = 0.5f, green = 0.0f, blue = 0.0f), style = Stroke(width = 100f))
            }
        }
    }
}