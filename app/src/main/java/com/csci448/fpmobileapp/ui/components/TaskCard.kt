package com.csci448.fpmobileapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.fpmobileapp.data.Task
import java.time.LocalDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


@Composable
fun TaskCard(
    task: Task,
    onCheckedChange: (Boolean) -> Unit = {},
    enabled: Boolean = true
) {
    var isChecked by remember { mutableStateOf(task.completed) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onCheckedChange(it)
                },
                enabled   = enabled
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    fontSize = 20.sp
                )
                Text(
                    text = "Due: ${task.timeDue}"
                )
            }

            Text(
                text = "$${task.coins}",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}
