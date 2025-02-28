package com.csci448.fpmobileapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavButton(inPlaceText: String = "placeholder", buttonAction: ()->Unit){
    Button(onClick = buttonAction) {
        Text(inPlaceText)
    }
}

@Preview
@Composable
private fun PreviewNavButton(){
    NavButton("Next", {})
}