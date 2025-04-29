package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.ui.components.NavButton
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * controls the app settings
 *
 * TODO:
 *  reorganize,
 *  actually implement settings
 */
@Composable
fun SettingScreen(
    viewModel: StudySaurusVM,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        NavButton(stringResource(id = R.string.label_signout)) {
            // reset any auth/session state here if needed...
            viewModel.setCurrentScreen(SelectedScreen.STARTUP)
            onSignOut()
        }
    }
}
