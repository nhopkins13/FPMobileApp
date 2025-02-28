package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
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
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.ui.components.Credential
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

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
fun LoginScreen(viewModel : StudySaurusVM, goToHome: () -> Unit) {
    Column {
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
            Button(onClick = { goToHome() }) {
                Text(text = stringResource(id = R.string.label_login))
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewLoginScreen(){
    LoginScreen(viewModel = StudySaurusVM(mySaurus = Saurus(
        name = TODO(),
        type = TODO(),
        size = TODO(),
        hat = TODO(),
        color = TODO()
    )), goToHome = {})
}