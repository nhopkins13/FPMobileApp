package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
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
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewTaskScreen(){
    TaskScreen(viewModel = StudySaurusVM(SaurusRepo.mySaurus))
}