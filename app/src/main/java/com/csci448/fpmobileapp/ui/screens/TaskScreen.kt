package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
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

    Column(modifier = modifier.fillMaxSize()) {
        if (tasks.isEmpty()) {
            Text(
                text = stringResource(R.string.no_tasks_label),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(tasks) { task ->
                    TaskCard(
                        task       = task,
                        onCheckedChange = { checked ->
                            viewModel.updateTask(task.copy(completed = checked))
                        }
                    )
                }
            }

            Button(
                onClick = { viewModel.archiveCompletedTasks() },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.archive_completed_tasks))
            }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewTaskScreen(){
}