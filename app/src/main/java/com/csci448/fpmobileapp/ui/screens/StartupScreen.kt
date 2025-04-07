package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.DinosaurImage
import com.csci448.fpmobileapp.ui.components.LogoImage
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
    Column(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp)){
        Box(modifier = Modifier.fillMaxWidth()
            .height(100.dp).clipToBounds(),
            contentAlignment = Alignment.Center){
            LogoImage(500.dp)
        }
        Spacer(modifier = Modifier.height((-300).dp))
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .widthIn(min = 500.dp, max = 900.dp)
                .fillMaxHeight(.70f)
                .background(Color(0xFFADD8E6))
                .border(width = 3.dp, color = Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            DinosaurCanvas(modifier, viewModel)
        }
        Spacer(modifier = Modifier.weight(1f))

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
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewStartupScreen(){
}