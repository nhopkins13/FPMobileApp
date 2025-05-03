package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskScreen(
    viewModel: StudySaurusVM,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    val currentTitle = remember { mutableStateOf("") }
    val showDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(yearRange = 2025..2050)
    val selectedDate = datePickerState.selectedDateMillis
    val coinWeight = remember { mutableStateOf(5) }

    Column(modifier = modifier.padding(16.dp)) {
        // Title
        Text(text = stringResource(R.string.title), fontSize = 20.sp)
        OutlinedTextField(
            value = currentTitle.value,
            onValueChange = { currentTitle.value = it },
            label = { Text(text = stringResource(R.string.title)) },
            modifier = Modifier.fillMaxWidth()
        )

        // Date
        Text(text = stringResource(R.string.date), fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedDate?.let { LocalDate.ofEpochDay(it / 86400000).toString() } ?: "",
                onValueChange = {},
                label = { Text(stringResource(R.string.due_date)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Select date")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            if (showDatePicker.value) {
                Popup(
                    alignment = Alignment.TopStart,
                    onDismissRequest = { showDatePicker.value = false }
                ) {
                    DatePicker(state = datePickerState, showModeToggle = false)
                }
            }
        }

        // Coins
        Text(text = stringResource(R.string.coins), fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = coinWeight.value == 5,
                    onClick = { coinWeight.value = 5 }
                )
                Text(text = "$5")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = coinWeight.value == 10,
                    onClick = { coinWeight.value = 10 }
                )
                Text(text = "$10")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = coinWeight.value == 25,
                    onClick = { coinWeight.value = 25 }
                )
                Text(text = "$25")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = coinWeight.value == 50,
                    onClick = { coinWeight.value = 50 }
                )
                Text(text = "$50")
            }
        }

        // Submit Button
        Button(
            onClick = {
                val dueDate = selectedDate?.let { LocalDate.ofEpochDay(it / 86400000) } ?: LocalDate.now()
                val task = Task(
                    title = currentTitle.value,
                    timeDue = dueDate,
                    coins = coinWeight.value
                )
                viewModel.addTask(task)
                onButtonClick()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(R.string.create_task))
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun NewTaskScreenPreview(){
}