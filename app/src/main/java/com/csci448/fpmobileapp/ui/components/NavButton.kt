package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavButton(inPlaceText: String = "placeholder", buttonAction: () -> Unit) {
    Button(
        onClick = buttonAction,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4B0082)
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.width(200.dp).height(50.dp)

    ) {
        Text(
            text = inPlaceText,
            color = Color.White
        )
    }
}



@Preview
@Composable
private fun PreviewNavButton(){
    NavButton("Next", {})
}