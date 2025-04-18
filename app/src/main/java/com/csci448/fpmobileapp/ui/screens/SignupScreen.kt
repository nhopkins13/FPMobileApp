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
import com.csci448.fpmobileapp.ui.components.NavButton
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/** SignupScreen
 *  prompts the user to login with username/email and password
 *  using Credential components
 *
 *  TODO:
 *      Improve formatting,
 *      connect to ViewModel,
 *      add buttons (back, submit, etc.),
 *      send inputs to login server(s),
 *
 **/
@Composable
fun SignupScreen(viewModel : StudySaurusVM, goToHome: () -> Unit, modifier: Modifier = Modifier){
    Column(modifier = modifier){
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(stringResource(R.string.label_signup))
        }
        Box(modifier = Modifier.padding(vertical = 15.dp),
            contentAlignment = Alignment.Center) {
            Credential(0, stringResource(R.string.label_email))
        }
        Box(modifier = Modifier.padding(vertical = 15.dp),
            contentAlignment = Alignment.Center) {
            Credential(0, stringResource(R.string.label_username))
        }
        Box(modifier = Modifier.padding(vertical = 15.dp),
            contentAlignment = Alignment.Center) {
            Credential(1, stringResource(R.string.label_password))
        }
        Box(modifier = Modifier.padding(vertical = 15.dp),
            contentAlignment = Alignment.Center) {
            Credential(1, stringResource(R.string.label_password_confirm))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            NavButton(stringResource(R.string.label_signup), { goToHome() })
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewSignupScreen(){

}