package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DinosaurCanvas(modifier: Modifier = Modifier){
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
    ){
        drawRect(
            color = Color.Gray
        )
        val centerX = size.width/2
        val centerY = size.height/2
        val dinoBackColor = Color(0.25f, 0.5f, 0.25f)
        val dinoFrontColor = Color(0.375f, 0.75f, 0.375f)
        val dinoClawColor = Color(0.6f, 0.75f, 0.375f)
        val dinoLineColor= Color(0.05f, 0.25f, 0.05f)
        withTransform({
            translate(left = -size.width/3)
            rotate(degrees=-20f)

        }){
            scale(scaleX = .15f, scaleY = .03f) {
                drawRect(
                    color = dinoBackColor
                )
            }
            val leftClawPath = Path() // path for left arm claws
            leftClawPath.moveTo(centerX - size.width*.075f, centerY - size.height*0.015f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY - size.height*0.01f)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY - size.height * 0.005f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.005f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY + size.height * 0.01f)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.015f)
            leftClawPath.close()
            drawPath(leftClawPath, dinoClawColor)

            val leftArmLine = Path() // outline on left arm
            leftArmLine.moveTo(centerX + size.width*0.075f, centerY - size.height*0.015f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.015f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY-size.height*0.01f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.005f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.005f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY + size.height*0.01f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.015f)
            leftArmLine.lineTo(centerX + size.width*0.075f, centerY + size.height*0.015f)
            drawPath(leftArmLine, dinoLineColor, style=Stroke(width=3f))
        }

        withTransform({
            translate(left = -size.height/8, top = size.height / 4)
            rotate(degrees=-20f)
        }){
            scale(scaleX = 0.2f, scaleY = 0.15f) {
                drawCircle(
                    color = dinoBackColor
                )
                drawCircle(
                    style = Stroke(width = 15f),
                    color = dinoLineColor
                )
            }

            val leftLegPath = Path()
            leftLegPath.moveTo(centerX-size.width*0.1f, centerY) // start at thigh
            leftLegPath.lineTo(centerX-size.width*0.075f, centerY+size.height*0.1f) // backwards knee
            leftLegPath.lineTo(centerX-size.width*0.135f, centerY+size.height*0.135f) // front of ankle
            leftLegPath.lineTo(centerX-size.width*0.165f, centerY+size.height*0.135f) // top of foot
            leftLegPath.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f) // bottom of foot
            leftLegPath.lineTo(centerX-size.width*0.125f, centerY+size.height*0.16f) // back of ankle
            leftLegPath.lineTo(centerX-size.width*0.03f, centerY+size.height*0.11f) // back of backwards knee
            leftLegPath.lineTo(centerX+size.width*0.0f, centerY+size.height*0.05f) // back to thigh
            drawPath(leftLegPath, dinoBackColor)

            val leftTalonPath = Path() // left foot claws
            leftTalonPath.moveTo(centerX-size.width*0.165f, centerY+size.height*0.135f)
            leftTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.138f)
            leftTalonPath.lineTo(centerX-size.width*0.1633f, centerY+size.height*0.142f)
            leftTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.145f)
            leftTalonPath.lineTo(centerX-size.width*0.1616f, centerY+size.height*0.148f)
            leftTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.152f)
            leftTalonPath.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f)
            drawPath(leftTalonPath, dinoClawColor)

            val leftLegLine = Path() // left leg outline
            leftLegLine.moveTo(centerX - size.width*0.1f, centerY)
            leftLegLine.lineTo(centerX-size.width*0.075f, centerY+size.height*0.1f)
            leftLegLine.lineTo(centerX-size.width*0.135f, centerY+size.height*0.135f)
            leftLegLine.lineTo(centerX-size.width*0.165f, centerY+size.height*0.135f)
            leftLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.138f)
            leftLegLine.lineTo(centerX-size.width*0.1633f, centerY+size.height*0.142f)
            leftLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.145f)
            leftLegLine.lineTo(centerX-size.width*0.1616f, centerY+size.height*0.148f)
            leftLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.152f)
            leftLegLine.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f)
            leftLegLine.lineTo(centerX-size.width*0.125f, centerY+size.height*0.16f)
            leftLegLine.lineTo(centerX-size.width*0.03f, centerY+size.height*0.11f)
            leftLegLine.lineTo(centerX+size.width*0.0f, centerY+size.height*0.05f)
            drawPath(leftLegLine, dinoLineColor, style=Stroke(width=3f))
        }

        // TODO: body, tail, other arm; teeth?
        // Head outline being an arc instead of a circle should allow for the body to go under the head 

        withTransform({
            translate(left=-size.width/4, top=-size.height/4)
        }) {
            scale(scaleX = 0.25f, scaleY = 0.25f) {
                drawCircle(color = dinoFrontColor) // main head part
                scale(scaleX = 1.0f, scaleY = size.width/size.height){
                    drawArc(
                        color = dinoLineColor,
                        startAngle = 170f,
                        sweepAngle = 200f,
                        useCenter = false,
                        style = Stroke(width = 12f)
                    )
                }
                //drawCircle(color = dinoLineColor, style=Stroke(width=12f))
            }
            translate(left = -size.width/20, top=size.height/24) {
                scale(scaleX = 0.25f, scaleY = 0.15f) {
                    drawCircle(color = dinoFrontColor) // snout
                    scale(scaleX = 1.0f, scaleY = size.width/size.height){
                        drawArc(
                            color = dinoLineColor,
                            startAngle = 45f,
                            sweepAngle = 190f,
                            useCenter = false,
                            style = Stroke(width=12f)
                        )
                    }
                }
                translate(left=-size.width/16, top=-size.height/48){
                    scale(scaleX = 0.25f, scaleY = .15f * size.width/size.height){
                        drawArc(
                            color = dinoLineColor,
                            startAngle = 45f,
                            sweepAngle = 65f,
                            useCenter = false,
                            style = Stroke(width = 20f)
                        ) // mouth
                    }
                }
            }
            translate(left = -3*size.width/20, top=size.height/24){
                scale(scaleX=0.015f, scaleY=0.025f){
                    drawCircle(color = dinoLineColor) // nostril
                    translate(left = size.width*3){
                        drawCircle(color = dinoLineColor)
                    }
                }
            }
            scale(scaleX = 0.025f, scaleY = 0.05f){ // eyes
                translate(left = -size.width*3, top = -size.height/2){
                    drawCircle(color = Color.White)
                    translate(top=size.height/12) {
                        scale(scaleX = 0.75f, scaleY = 0.75f) {
                            drawCircle(color = Color.Black)
                        }
                    }
                }
                translate(top = -size.height/2){
                    drawCircle(color = Color.White)
                    translate(top=size.height/12) {
                        scale(scaleX = 0.75f, scaleY = 0.75f) {
                            drawCircle(color = Color.Black)
                        }
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun DinosaurCanvasPreview(){
    DinosaurCanvas()
}