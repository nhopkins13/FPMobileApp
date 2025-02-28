package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.ui.components.NavButton

/**
 * The first screen to appear when the app opens
 *
 * TODO:
 *  connect to viewmodel,
 *  add lambdas to buttons to navigate between screens,
 *  refine visuals,
 *  move displayed text to strings.xml
 */
@Composable
fun StartupScreen(goToLogin: () -> Unit, goToSignup: ()-> Unit, skip: ()-> Unit){
    Column{
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text("STUDYSAURUS")
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            Text("[logo/mascot goes here]")
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton("Login", goToLogin)
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton("Sign up", goToSignup)
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton("continue offline", skip)
        }
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
    }
}

@Preview
@Composable
private fun PreviewStartupScreen(){
    StartupScreen({}, {}, {})
}