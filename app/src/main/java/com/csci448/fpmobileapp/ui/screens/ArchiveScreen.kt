package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.R
import com.csci448.fpmobileapp.ui.components.TaskCard
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

// com/csci448/fpmobileapp/ui/screens/ArchiveScreen.kt

@Composable
fun ArchiveScreen(viewModel: StudySaurusVM, modifier: Modifier = Modifier) {
    val archived by viewModel.archivedTasks.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        if (archived.isEmpty()) {
            Text(
                text = stringResource(R.string.no_archived_tasks),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(archived) { task ->
                    // disable interaction on archived items
                    TaskCard(
                        task            = task,
                        onCheckedChange = { /* no-op */ },
                        enabled         = false
                    )
                }
            }
        }
    }
}
