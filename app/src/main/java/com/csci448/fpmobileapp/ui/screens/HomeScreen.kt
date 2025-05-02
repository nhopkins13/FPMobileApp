package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
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
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.NavButton
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * The home screen of the app.
 * Displays the studysaurus, has notifications & related, as buttons to access other parts of app
 *
 * TODO:
 *  reorganize,
 *  add  more visuals
 */
@Composable
fun HomeScreen(
    viewModel : StudySaurusVM,
    toSettings: () -> Unit = {},
    toWardrobe: () -> Unit = {},
    toShop: () -> Unit = {},
    toTasks: () -> Unit = {},
    toSocial: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val tasks by viewModel.taskList.collectAsState()
    val uncompleted = tasks.filter { !it.completed }
    val nextTask = uncompleted
        .minWithOrNull(
            compareBy<Task> { it.timeDue }
                .thenByDescending { it.coins }
        )

    Column(modifier = modifier) {
        nextTask?.let { task ->
            Text(
                text = stringResource(R.string.upcoming_label),
                modifier = Modifier.padding(8.dp)
            )
            TaskCard(
                task = task,
                onCheckedChange = { isChecked ->
                    viewModel.updateTask(task.copy(completed = isChecked))
                }
            )
        }

        Box(modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            //DinosaurImage(600.dp)
            DinosaurCanvas(viewModel = viewModel)
        }

    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewHomeScreen(){
}