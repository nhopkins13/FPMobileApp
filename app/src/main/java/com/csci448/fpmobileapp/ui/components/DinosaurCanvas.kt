package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.clothing.DinoBelt
import com.csci448.fpmobileapp.ui.components.clothing.DinoCrown
import com.csci448.fpmobileapp.ui.components.clothing.DinoHat
import com.csci448.fpmobileapp.ui.components.clothing.DinoPearls
import com.csci448.fpmobileapp.ui.components.clothing.DinoSkirt
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun DinosaurCanvas(modifier: Modifier = Modifier, saurus: Saurus){
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
    ){
        val centerX = size.width/2
        val centerY = size.height/2
        val dinoBackColor = Color(0.25f, 0.5f, 0.25f)
        val dinoFrontColor = Color(0.375f, 0.75f, 0.375f)
        val dinoClawColor = Color(0.6f, 0.75f, 0.375f)
        val dinoLineColor= Color(0.05f, 0.25f, 0.05f)
        withTransform({
            translate(left = -size.width/3, top = size.height * 0.025f)
            rotate(degrees=-20f)

        }){
            scale(scaleX = .15f, scaleY = .03f) {
                drawRect(
                    color = dinoBackColor
                )
            }
            val leftClawPath = Path() // path for left arm claws
            leftClawPath.moveTo(centerX - size.width*.075f, centerY - size.height*0.015f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY - size.height*0.005f)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY - size.height * 0.005f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY + size.height * 0.005f)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.005f)
            leftClawPath.lineTo(centerX - size.width*.1f, centerY + size.height * 0.015f)
            leftClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.015f)
            leftClawPath.close()
            drawPath(leftClawPath, dinoClawColor)

            val leftArmLine = Path() // outline on left arm
            leftArmLine.moveTo(centerX + size.width*0.075f, centerY - size.height*0.015f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.015f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY-size.height*0.005f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.005f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY + size.height* 0.005f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.005f)
            leftArmLine.lineTo(centerX - size.width*.1f, centerY + size.height*0.015f)
            leftArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.015f)
            leftArmLine.lineTo(centerX + size.width*0.075f, centerY + size.height*0.015f)
            drawPath(leftArmLine, dinoLineColor, style=Stroke(width=3f))
        }

        withTransform({
            translate(left = -size.height * 0.125f, top = size.height * 0.2f)
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

        // Neck
        val neckPath = Path()
        neckPath.moveTo(centerX - size.width * 0.0625f, centerY)
        neckPath.lineTo(centerX - size.width * 0.125f, centerY - size.height * 0.1875f)
        neckPath.lineTo(centerX - size.width * 0.25f, centerY - size.height * 0.125f)
        neckPath.lineTo(centerX - size.width * 0.3f, centerY)
        drawPath(neckPath, dinoFrontColor)

        val neckLine = Path()
        neckLine.moveTo(centerX - size.width * 0.0625f, centerY)
        neckLine.lineTo(centerX - size.width * 0.125f, centerY - size.height * 0.1875f)
        neckLine.lineTo(centerX - size.width * 0.25f, centerY - size.height * 0.125f)
        neckLine.lineTo(centerX - size.width * 0.3f, centerY)
        drawPath(neckLine, dinoLineColor, style = Stroke(width = 3f))

        // Body
        translate(left = -size.width * 0.125f, top = size.height * 0.1f) {
            rotate(degrees = -20f) {
                scale(scaleX = 0.35f, scaleY = 0.55f) {
                    drawCircle(color = dinoFrontColor)
                    scale(scaleX = 1.0f, scaleY = size.width / size.height){
                        drawArc(
                            color = dinoLineColor,
                            startAngle = -46f,
                            sweepAngle = 288f,
                            useCenter = false,
                            style = Stroke(width = 6f)
                        )
                    }
                }
            }
        }

        // Head
        withTransform({
            translate(left=-size.width/4, top=-size.height * 0.1875f)
        }) {
            scale(scaleX = 0.25f, scaleY = 0.25f) {
                drawCircle(color = dinoFrontColor) // main head part
                scale(scaleX = 1.0f, scaleY = size.width/size.height){
                    drawArc(
                        color = dinoLineColor,
                        startAngle = 170f,
                        sweepAngle = 192f,
                        useCenter = false,
                        style = Stroke(width = 12f)
                    )
                }
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

        // Right leg
        withTransform({
            translate(left = -size.height * 0.1f, top = size.height * 0.25f )
            rotate(degrees=-20f)
        }){
            scale(scaleX = 0.2f, scaleY = 0.15f) {
                drawCircle(
                    color = dinoFrontColor
                )
                scale(scaleX = 1.0f, scaleY = size.width/size.height){
                    drawArc(
                        color = dinoLineColor,
                        startAngle = 25f,
                        sweepAngle = 250f,
                        useCenter = false,
                        style = Stroke(15f)
                    )
                }

            }

            val rightLegPath = Path()
            rightLegPath.moveTo(centerX-size.width*0.1f, centerY) // start at thigh
            rightLegPath.lineTo(centerX-size.width*0.075f, centerY+size.height*0.1f) // backwards knee
            rightLegPath.lineTo(centerX-size.width*0.135f, centerY+size.height*0.135f) // front of ankle
            rightLegPath.lineTo(centerX-size.width*0.165f, centerY+size.height*0.135f) // top of foot
            rightLegPath.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f) // bottom of foot
            rightLegPath.lineTo(centerX-size.width*0.11f, centerY+size.height*0.16f) // back of ankle
            rightLegPath.lineTo(centerX-size.width*0.01f, centerY+size.height*0.11f) // back of backwards knee
            rightLegPath.lineTo(centerX+size.width*0.0f, centerY+size.height*0.05f) // back to thigh
            drawPath(rightLegPath, dinoFrontColor)

            val rightTalonPath = Path() // left foot claws
            rightTalonPath.moveTo(centerX-size.width*0.165f, centerY+size.height*0.135f)
            rightTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.138f)
            rightTalonPath.lineTo(centerX-size.width*0.1633f, centerY+size.height*0.142f)
            rightTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.145f)
            rightTalonPath.lineTo(centerX-size.width*0.1616f, centerY+size.height*0.148f)
            rightTalonPath.lineTo(centerX-size.width*0.19f, centerY+size.height*0.152f)
            rightTalonPath.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f)
            drawPath(rightTalonPath, dinoClawColor)

            val rightLegLine = Path() // left leg outline
            rightLegLine.moveTo(centerX - size.width*0.1f, centerY)
            rightLegLine.lineTo(centerX-size.width*0.075f, centerY+size.height*0.1f)
            rightLegLine.lineTo(centerX-size.width*0.135f, centerY+size.height*0.135f)
            rightLegLine.lineTo(centerX-size.width*0.165f, centerY+size.height*0.135f)
            rightLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.138f)
            rightLegLine.lineTo(centerX-size.width*0.1633f, centerY+size.height*0.142f)
            rightLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.145f)
            rightLegLine.lineTo(centerX-size.width*0.1616f, centerY+size.height*0.148f)
            rightLegLine.lineTo(centerX-size.width*0.19f, centerY+size.height*0.152f)
            rightLegLine.lineTo(centerX-size.width*0.16f, centerY+size.height*0.155f)
            rightLegLine.lineTo(centerX-size.width*0.11f, centerY+size.height*0.16f)
            rightLegLine.lineTo(centerX-size.width*0.01f, centerY+size.height*0.11f)
            rightLegLine.lineTo(centerX+size.width*0.0f, centerY+size.height*0.05f)
            drawPath(rightLegLine, dinoLineColor, style=Stroke(width=3f))
        }

        // Right arm
        withTransform({
            translate(left = -size.width * 0.25f, top = size.height * 0.05f)
            rotate(degrees=-20f)
        }){
            scale(scaleX = .15f, scaleY = .03f) {
                drawRect(
                    color = dinoFrontColor
                )
            }
            val rightClawPath = Path() // path for right arm claws
            rightClawPath.moveTo(centerX - size.width*.075f, centerY - size.height*0.015f)
            rightClawPath.lineTo(centerX - size.width*.1f, centerY - size.height*0.005f)
            rightClawPath.lineTo(centerX - size.width*.075f, centerY - size.height * 0.005f)
            rightClawPath.lineTo(centerX - size.width*.1f, centerY + size.height * 0.005f)
            rightClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.005f)
            rightClawPath.lineTo(centerX - size.width*.1f, centerY + size.height * 0.015f)
            rightClawPath.lineTo(centerX - size.width*.075f, centerY + size.height*0.015f)
            rightClawPath.close()
            drawPath(rightClawPath, dinoClawColor)

            val rightArmLine = Path() // outline on right arm
            rightArmLine.moveTo(centerX + size.width*0.075f, centerY - size.height*0.015f)
            rightArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.015f)
            rightArmLine.lineTo(centerX - size.width*.1f, centerY-size.height*0.005f)
            rightArmLine.lineTo(centerX - size.width*0.075f, centerY-size.height*0.005f)
            rightArmLine.lineTo(centerX - size.width*.1f, centerY + size.height* 0.005f)
            rightArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.005f)
            rightArmLine.lineTo(centerX - size.width*.1f, centerY + size.height*0.015f)
            rightArmLine.lineTo(centerX - size.width*0.075f, centerY + size.height*0.015f)
            rightArmLine.lineTo(centerX + size.width*0.075f, centerY + size.height*0.015f)
            drawPath(rightArmLine, dinoLineColor, style=Stroke(width=3f))
        }

        // Tail
        val tailPath = Path()
        tailPath.moveTo(centerX - size.width * 0.05f, centerY + size.height * 0.275f)
        tailPath.lineTo(centerX + size.width * 0.25f, centerY + size.height * 0.3f)
        tailPath.lineTo(centerX + size.width * 0.35f, centerY + size.height * 0.28f)
        tailPath.lineTo(centerX + size.width * 0.4f, centerY + size.height * 0.25f)
        tailPath.lineTo(centerX + size.width * 0.41f, centerY + size.height * 0.225f)
        tailPath.lineTo(centerX + size.width * 0.375f, centerY + size.height * 0.24f)
        tailPath.lineTo(centerX + size.width * 0.325f, centerY + size.height * 0.25f)
        tailPath.lineTo(centerX + size.width * 0.25f, centerY + size.height * 0.24f)
        tailPath.lineTo(centerX + size.width * 0.12f, centerY + size.height * 0.2f)
        tailPath.lineTo(centerX + size.width * 0.057f, centerY + size.height * 0.1f)
        drawPath(tailPath, dinoFrontColor)

        val tailLine = Path()
        tailLine.moveTo(centerX - size.width * 0.05f, centerY + size.height * 0.275f)
        tailLine.lineTo(centerX + size.width * 0.25f, centerY + size.height * 0.3f)
        tailLine.lineTo(centerX + size.width * 0.35f, centerY + size.height * 0.28f)
        tailLine.lineTo(centerX + size.width * 0.4f, centerY + size.height * 0.25f)
        tailLine.lineTo(centerX + size.width * 0.41f, centerY + size.height * 0.225f)
        tailLine.lineTo(centerX + size.width * 0.375f, centerY + size.height * 0.24f)
        tailLine.lineTo(centerX + size.width * 0.325f, centerY + size.height * 0.25f)
        tailLine.lineTo(centerX + size.width * 0.25f, centerY + size.height * 0.24f)
        tailLine.lineTo(centerX + size.width * 0.12f, centerY + size.height * 0.2f)
        tailLine.lineTo(centerX + size.width * 0.057f, centerY + size.height * 0.1f)
        drawPath(tailLine, dinoLineColor, style = Stroke(width=3f))


        withTransform({
            translate(left=-size.width * 0.25f, top=-size.height * 0.25f)
            scale(scaleX = 0.25f, scaleY = 0.25f)
        }){
            when (saurus.hat) {
                1 -> DinoHat(this, size)
                2 -> DinoCrown(this, size)
                //more hats go here
            }
        }

        withTransform({
            rotate(degrees = -15f)
            translate(left = -size.width * 0.16f, top = size.height * 0.1f)
        }){
            when (saurus.belt) {
                3 -> DinoBelt(this, size)
                4 -> DinoSkirt(this, size)
                // more belts here
            }
        }

        withTransform({
            translate(left = -size.width * 0.18125f, top = -size.height * 0.075f)
            rotate(degrees = -10f)
        }){
            when (saurus.neckWear) {
                5 -> DinoPearls(this, size)
            }
        }
    }
}


@Preview
@Composable
fun DinosaurCanvasPreview(){
    val testSaurus = Saurus(name = "Saurus", hat=2, belt=4, neckWear=5)
    DinosaurCanvas(saurus = testSaurus)
}
