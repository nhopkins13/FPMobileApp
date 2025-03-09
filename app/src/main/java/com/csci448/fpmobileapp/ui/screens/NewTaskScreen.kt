package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun NewTaskScreen(viewModel: StudySaurusVM, modifier: Modifier = Modifier){
    Column(modifier = modifier){
        Text(
            text = "NEW TASK SCREEN"
        )
    }
}