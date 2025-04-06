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
import androidx.compose.runtime.MutableState
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
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskScreen(viewModel: StudySaurusVM, modifier: Modifier = Modifier, onButtonClick: () -> Unit){
    val currentTitle: MutableState<String> = remember {mutableStateOf("")}
    val showDatePicker: MutableState<Boolean> = remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState(yearRange = IntRange(2025, 2050))
    val selectedDate = datePickerState.selectedDateMillis
    var coinWeight = 5

    Column(modifier = modifier
        .padding(8.dp)
    ){
        // Title
        Text(
            text = stringResource(R.string.title),
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = currentTitle.value,
            onValueChange = {
                currentTitle.value = it
            }
        )
        // Date
        Text(
            text = stringResource(R.string.date),
            fontSize = 20.sp
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
        ){
            OutlinedTextField(
                value = LocalDate.ofEpochDay((selectedDate?:0) / 86400000).toString(),
                onValueChange = {},
                label = {Text("")},
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {showDatePicker.value = !showDatePicker.value}){
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = modifier
            )
            if(showDatePicker.value){
                Popup(
                    onDismissRequest = {showDatePicker.value = false},
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = modifier
                    ){
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
        }

        // Coins
        Text(
            text = stringResource(R.string.coins),
            fontSize = 20.sp
        )
        Row(
            modifier = modifier
        ){
            RadioButton(
                selected = coinWeight == 5,
                onClick = {
                    coinWeight = 5
                }
            )
            Text(
                text = "$5"
            )
            RadioButton(
                selected = coinWeight == 10,
                onClick = {
                    coinWeight = 10
                }
            )
            Text(
                text = "$10"
            )
            RadioButton(
                selected = coinWeight == 25,
                onClick = {
                    coinWeight = 25
                }
            )
            Text(
                text = "$25"
            )
            RadioButton(
                selected = coinWeight == 50,
                onClick = {
                    coinWeight = 50
                }
            )
            Text(
                text = "$50"
            )

        }

        Button(
            onClick = {
                val task = Task(title = currentTitle.value, timeDue = LocalDate.ofEpochDay((selectedDate?:0) / 864000), coins = coinWeight)
                viewModel.addTask(task)
                onButtonClick()
            }
        ){
            Text(
                text = "Create task"
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun NewTaskScreenPreview(){
    val model = StudySaurusVM(SaurusRepo.mySaurus)
    NewTaskScreen(viewModel = model, onButtonClick = {})
}