package com.csci448.fpmobileapp.ui.components.clothing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform

fun DinoSkirt(drawScope: DrawScope, size: Size){
    drawScope.withTransform({

    }){
        scale(scaleX = 0.35f, scaleY = 0.025f){
            drawRect(color = Color(red = 0.9f, green = 0.3f, blue = 0.7f))
        }
        val skirtPath = Path()
        skirtPath.moveTo(size.width * 0.325f, size.height * 0.5125f)
        skirtPath.lineTo(size.width * 0.2f, size.height * 0.625f)

        skirtPath.lineTo(size.width * 0.23f, size.height * 0.62f)
        skirtPath.lineTo(size.width * 0.26f, size.height * 0.63f)
        skirtPath.lineTo(size.width * 0.29f, size.height * 0.625f)
        skirtPath.lineTo(size.width * 0.32f, size.height * 0.635f)
        skirtPath.lineTo(size.width * 0.35f, size.height * 0.63f)
        skirtPath.lineTo(size.width * 0.38f, size.height * 0.64f)
        skirtPath.lineTo(size.width * 0.41f, size.height * 0.635f)
        skirtPath.lineTo(size.width * 0.44f, size.height * 0.645f)
        skirtPath.lineTo(size.width * 0.47f, size.height * 0.64f)
        skirtPath.lineTo(size.width * 0.50f, size.height * 0.65f)
        skirtPath.lineTo(size.width * 0.53f, size.height * 0.64f)
        skirtPath.lineTo(size.width * 0.56f, size.height * 0.645f)
        skirtPath.lineTo(size.width * 0.59f, size.height * 0.635f)
        skirtPath.lineTo(size.width * 0.62f, size.height * 0.64f)
        skirtPath.lineTo(size.width * 0.65f, size.height * 0.63f)
        skirtPath.lineTo(size.width * 0.68f, size.height * 0.635f)
        skirtPath.lineTo(size.width * 0.71f, size.height * 0.625f)
        skirtPath.lineTo(size.width * 0.74f, size.height * 0.63f)
        skirtPath.lineTo(size.width * 0.77f, size.height * 0.62f)

        skirtPath.lineTo(size.width * 0.8f, size.height * 0.625f)
        skirtPath.lineTo(size.width * 0.675f, size.height * 0.5125f)

        drawPath(skirtPath, Color(red = 1.0f, green = 0.5f, blue = 0.8f))

        val skirtOutline = Path()
        skirtOutline.moveTo(size.width * 0.325f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.2f, size.height * 0.625f)

        skirtOutline.lineTo(size.width * 0.23f, size.height * 0.62f)
        skirtOutline.lineTo(size.width * 0.3425f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.23f, size.height * 0.62f)

        skirtOutline.lineTo(size.width * 0.26f, size.height * 0.63f)
        skirtOutline.lineTo(size.width * 0.36f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.26f, size.height * 0.63f)

        skirtOutline.lineTo(size.width * 0.29f, size.height * 0.625f)
        skirtOutline.lineTo(size.width * 0.3775f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.29f, size.height * 0.625f)

        skirtOutline.lineTo(size.width * 0.32f, size.height * 0.635f)
        skirtOutline.lineTo(size.width * 0.395f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.32f, size.height * 0.635f)

        skirtOutline.lineTo(size.width * 0.35f, size.height * 0.63f)
        skirtOutline.lineTo(size.width * 0.4125f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.35f, size.height * 0.63f)

        skirtOutline.lineTo(size.width * 0.38f, size.height * 0.64f)
        skirtOutline.lineTo(size.width * 0.43f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.38f, size.height * 0.64f)

        skirtOutline.lineTo(size.width * 0.41f, size.height * 0.635f)
        skirtOutline.lineTo(size.width * 0.4475f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.41f, size.height * 0.635f)

        skirtOutline.lineTo(size.width * 0.44f, size.height * 0.645f)
        skirtOutline.lineTo(size.width * 0.465f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.44f, size.height * 0.645f)

        skirtOutline.lineTo(size.width * 0.47f, size.height * 0.64f)
        skirtOutline.lineTo(size.width * 0.4825f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.47f, size.height * 0.64f)

        skirtOutline.lineTo(size.width * 0.50f, size.height * 0.65f)
        skirtOutline.lineTo(size.width * 0.50f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.50f, size.height * 0.65f)

        skirtOutline.lineTo(size.width * 0.53f, size.height * 0.64f)
        skirtOutline.lineTo(size.width * 0.5175f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.53f, size.height * 0.64f)

        skirtOutline.lineTo(size.width * 0.56f, size.height * 0.645f)
        skirtOutline.lineTo(size.width * 0.535f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.56f, size.height * 0.645f)

        skirtOutline.lineTo(size.width * 0.59f, size.height * 0.635f)
        skirtOutline.lineTo(size.width * 0.5525f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.59f, size.height * 0.635f)

        skirtOutline.lineTo(size.width * 0.62f, size.height * 0.64f)
        skirtOutline.lineTo(size.width * 0.57f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.62f, size.height * 0.64f)

        skirtOutline.lineTo(size.width * 0.65f, size.height * 0.63f)
        skirtOutline.lineTo(size.width * 0.5875f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.65f, size.height * 0.63f)

        skirtOutline.lineTo(size.width * 0.68f, size.height * 0.635f)
        skirtOutline.lineTo(size.width * 0.605f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.68f, size.height * 0.635f)

        skirtOutline.lineTo(size.width * 0.71f, size.height * 0.625f)
        skirtOutline.lineTo(size.width * 0.6225f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.71f, size.height * 0.625f)

        skirtOutline.lineTo(size.width * 0.74f, size.height * 0.63f)
        skirtOutline.lineTo(size.width * 0.64f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.74f, size.height * 0.63f)

        skirtOutline.lineTo(size.width * 0.77f, size.height * 0.62f)
        skirtOutline.lineTo(size.width * 0.6575f, size.height * 0.5125f)
        skirtOutline.lineTo(size.width * 0.77f, size.height * 0.62f)

        skirtOutline.lineTo(size.width * 0.8f, size.height * 0.625f)
        skirtOutline.lineTo(size.width * 0.675f, size.height * 0.5125f)

        drawPath(skirtOutline, Color(red = 0.9f, green = 0.3f, blue = 0.7f), style = Stroke(width = 3f))
    }
}