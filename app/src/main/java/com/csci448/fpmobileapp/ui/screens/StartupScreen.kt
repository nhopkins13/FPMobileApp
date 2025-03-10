package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.NavButton
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

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
fun StartupScreen(viewModel : StudySaurusVM, goToHome: () -> Unit, goToLogin: () -> Unit, goToSignup: ()-> Unit, modifier: Modifier = Modifier){
    Column(modifier = modifier){
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(stringResource(R.string.label_app_name))
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            Text("[logo/mascot goes here]")
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton(stringResource(id = R.string.label_login), { goToLogin() })
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton(stringResource(id = R.string.label_signup), { goToSignup() })
        }
        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            NavButton(stringResource(id = R.string.label_offline), { goToHome() })
        }
        //Box(modifier = Modifier.weight(0.7f).fillMaxSize()){}
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewStartupScreen(){
    StartupScreen(viewModel = StudySaurusVM(mySaurus = SaurusRepo.mySaurus), goToHome = {}, goToLogin = {}, goToSignup = {})
}