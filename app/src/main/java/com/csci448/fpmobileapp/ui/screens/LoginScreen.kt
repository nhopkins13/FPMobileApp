package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.ui.components.Credential

/** LoginScreen
 *  prompts the user to login with username/email and password
 *  using Credential components
 *
 *  TODO:
 *      Improve formatting,
 *      connect to ViewModel,
 *      add buttons (back, submit, etc.),
 *      send inputs to login server(s),
 *
 */
@Composable
fun LoginScreen(){
    Column{
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
        Box(modifier = Modifier.weight(0.5f).fillMaxSize().padding(vertical = 15.dp),
            contentAlignment = Alignment.BottomCenter) {
            Credential(false)
        }
        Box(modifier = Modifier.weight(0.5f).fillMaxSize().padding(vertical = 15.dp)) {
            Credential(true)
        }
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
    }
}

@Preview
@Composable
private fun PreviewLoginScreen(){
    LoginScreen()
}