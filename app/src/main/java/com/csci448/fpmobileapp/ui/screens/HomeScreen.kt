// File: HomeScreen.kt

package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.* // Use wildcard import or add Box, weight if needed
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
import com.csci448.fpmobileapp.ui.components.DinosaurCanvas
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@Composable
fun HomeScreen(
    viewModel : StudySaurusVM,
    modifier: Modifier = Modifier // Assumes this modifier includes Scaffold padding
) {
    val tasks by viewModel.taskList.collectAsState()
    val currentUserProfile by viewModel.userProfile.collectAsState()
    val saurusState by viewModel.currentSaurusState

    val uncompleted = tasks.filter { !it.completed }
    val nextTask = uncompleted.minByOrNull { it.timeDue }

    Column(
        modifier = modifier // Apply modifier passed from Scaffold/NavHost first
            .fillMaxSize()
            // Add screen-specific horizontal padding *inside* the Scaffold padding
            .padding(horizontal = 16.dp)
    ) {
        // Welcome Message
        Text(
            text = "Welcome, ${currentUserProfile?.username ?: "User"}!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                // *** CHANGE HERE: Removed top padding ***
                // Rely on Scaffold padding for spacing below App Bar. Keep bottom padding.
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Upcoming Task Section (No changes needed)
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
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

        // Dinosaur Canvas Box - Will expand vertically now
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes all remaining vertical space
                .padding(bottom = 8.dp), // Keep padding below dino before nav bar
            contentAlignment = Alignment.Center
        ) {
            // DinosaurCanvas scales with aspect ratio within the larger Box
            DinosaurCanvas(
                modifier = Modifier,
                saurus = saurusState
            )
        }
    }
}