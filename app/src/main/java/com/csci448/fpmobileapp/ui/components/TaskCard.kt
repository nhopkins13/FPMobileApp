package com.csci448.fpmobileapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.fpmobileapp.data.Task
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun TaskCard(task: Task){
    Card(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Checkbox(
                checked = task.completed.value,
                onCheckedChange = {
                    task.completed.value = !task.completed.value
                }
            )
            Column() {
                Text(
                    text = task.title,
                    fontSize = 28.sp
                )
                Text(
                    text = task.timeDue.toString()
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$" + task.coins.toString(),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun TaskCardPreview(){
    TaskCard(Task(title="Homework", coins=10, timeDue= LocalDate.parse("2025-04-07"), completed = mutableStateOf(false)))
}