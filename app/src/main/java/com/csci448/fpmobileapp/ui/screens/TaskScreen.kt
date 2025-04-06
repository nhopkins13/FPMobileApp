package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun TaskScreen(viewModel : StudySaurusVM, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        //remove/change once page has content
        Text("TASK SCREEN")
        Text("nothing here")
        Text("hit back button in studio")
    }
    LazyColumn(modifier = modifier) {
        items(viewModel.taskList){ task ->
            TaskCard(task)
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewTaskScreen(){
    TaskScreen(viewModel = StudySaurusVM(SaurusRepo.mySaurus))
}