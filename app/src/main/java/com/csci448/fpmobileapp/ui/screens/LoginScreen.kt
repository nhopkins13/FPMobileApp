package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun LoginScreen(
    viewModel : StudySaurusVM,
    goToHome: () -> Unit,
    goToSignup: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authError by viewModel.authError.collectAsState()
    val isLoading by viewModel.isLoadingAuth.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()

    // Navigate when login succeeds
    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            goToHome()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.label_login), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.label_email)) }, // Use Email label
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.label_password)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display Error
        authError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Loading Indicator or Login Button
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.loginUser(email.trim(), password) }, // Trim email
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.label_login))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = goToSignup) { // Button to navigate to Signup
            Text("Don't have an account? Sign Up")
        }
    }
}
