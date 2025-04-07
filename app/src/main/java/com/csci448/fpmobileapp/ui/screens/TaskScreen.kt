package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * Lists the user's tasks
 * allows the user to keep track of task completion
 * allows the user to create their own tasks and task lists
 *
 * TODO:
 *  reorganize,
 *  actually implement task screen
 */
@Composable
fun TaskScreen(viewModel: StudySaurusVM, modifier: Modifier = Modifier) {
    val tasks by viewModel.taskList.collectAsState()

    Column(modifier = modifier) {
        if (tasks.isEmpty()) {
            Text("No tasks found! Add a new one to get started.")
        } else {
            LazyColumn {
                items(tasks) { task ->
                    TaskCard(task = task, onCheckedChange = { checked ->
                        viewModel.updateTask(task.copy(completed = checked))
                    })
                }

            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewTaskScreen(){
}