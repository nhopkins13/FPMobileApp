package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
// import androidx.compose.foundation.layout.aspectRatio // Import no longer needed

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
    val currentUserProfile by viewModel.userProfile.collectAsState()
    val saurusState by viewModel.currentSaurusState

    val uncompleted = tasks.filter { !it.completed }
    val nextTask = uncompleted.minByOrNull { it.timeDue }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Horizontal padding for edges
        // Apply vertical padding selectively below
    ) {
        // Welcome Message
        Text(
            text = "Welcome, ${currentUserProfile?.username ?: "User"}!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp) // Padding above and below Welcome
                .align(Alignment.CenterHorizontally)
        )

        // Upcoming Task Section
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) { // Add padding below task section
            Text(
                text = stringResource(R.string.upcoming_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (nextTask != null) {
                TaskCard(
                    task = nextTask,
                    onCheckedChange = { /* No action */ },
                    enabled = false
                )
            } else {
                Text(
                    text = stringResource(R.string.no_upcoming_tasks),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Dinosaur Canvas Box - Fills remaining space, NO fixed aspect ratio
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes all remaining vertical space
                // .aspectRatio(1f) // REMOVED aspect ratio constraint
                .padding(bottom = 8.dp), // Add padding below dino before nav bar might appear
            contentAlignment = Alignment.Center
        ) {
            DinosaurCanvas(
                modifier = Modifier.fillMaxSize(), // Canvas fills the available Box (now potentially rectangular)
                saurus = saurusState
            )
        }
    }
}