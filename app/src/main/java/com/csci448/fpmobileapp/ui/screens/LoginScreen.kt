package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
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
//parameter actually needs to be viewmodel and transport home will be defined there as well
fun LoginScreen(transportHome: () -> Unit){
    Column {
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("Log in")
        }
        Box(
            modifier = Modifier.padding(vertical = 15.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Credential(0, "Username or Email")
        }
        Box(modifier = Modifier.padding(vertical = 15.dp)) {
            Credential(1, "Password")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { transportHome() }) {
                Text(text = stringResource(id = R.string.label_login))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen(){
    LoginScreen(transportHome = {})
}