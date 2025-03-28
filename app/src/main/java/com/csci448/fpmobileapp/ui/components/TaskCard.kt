package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.Task

@Composable
fun TaskCard(task: Task){
    Card(modifier = Modifier) {
        Row() {
            Column() {
                Text(
                    text = task.title
                )
                Text(
                    text = task.timeDue
                )
            }
            Text(
                text = task.coins.toString(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview(){
    TaskCard(Task(title="Homework", coins=10, timeDue="2:00pm"))
}