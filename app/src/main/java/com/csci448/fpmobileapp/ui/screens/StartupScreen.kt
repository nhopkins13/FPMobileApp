package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.ui.components.LogoImage // Ensure this composable exists
import com.csci448.fpmobileapp.ui.components.NavButton // Ensure this composable exists
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun StartupScreen(
    viewModel: StudySaurusVM, // ViewModel might not be strictly needed here anymore
    goToHome: () -> Unit,
    goToLogin: () -> Unit,
    goToSignup: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround // Distribute space
    ) {

        LogoImage(size = 500.dp)



        // BUTTONS GROUPED AT THE BOTTOM
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NavButton(
                inPlaceText = stringResource(id = R.string.label_login),
                buttonAction = goToLogin
            )
            Spacer(modifier = Modifier.height(16.dp))
            NavButton(
                inPlaceText = stringResource(id = R.string.label_signup),
                buttonAction = goToSignup)
        }
    }
}

// Simple Preview without ViewModel if not needed
@Preview(showBackground = true)
@Composable
private fun PreviewStartupScreen() {
    StartupScreen(
        viewModel = /* Provide a fake/preview VM if necessary */ {} as StudySaurusVM,
        goToHome = {},
        goToLogin = {},
        goToSignup = {}
    )
}