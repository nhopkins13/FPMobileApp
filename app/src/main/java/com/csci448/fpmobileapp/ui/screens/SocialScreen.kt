package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM
import com.csci448.fpmobileapp.data.UserProfile

@Composable
fun SocialScreen(
    viewModel : StudySaurusVM,
    goToLogin: () -> Unit,
    modifier: Modifier = Modifier
){
    val userProfile by viewModel.userProfile.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        // Removed verticalArrangement = Arrangement.Center to place profile at top
    ){
        Spacer(modifier = Modifier.height(32.dp)) // Space from top

        // Profile Section
        UserProfileIcon(username = userProfile?.username)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = userProfile?.username ?: "Loading...",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = userProfile?.email ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant // Subtler color
        )

        Spacer(modifier = Modifier.weight(1f)) // Push logout button to bottom

        // Logout Button
        Button(
            onClick = {
                viewModel.logoutUser()
                goToLogin()
            },
            modifier = Modifier.fillMaxWidth(0.7f) // Match StartupScreen button width
        ) {
            Text("Logout")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Space from bottom
    }
}

@Composable
fun UserProfileIcon(username: String?) {
    val initial = username?.firstOrNull()?.uppercaseChar() ?: '?'
    // Generate a simple color based on the initial (or use a fixed color)
    val backgroundColor = remember(initial) {
        if (initial == '?') Color.Gray else {
            // Simple deterministic color generation
            val hue = (initial.code * 30) % 360f
            Color.hsl(hue, 0.5f, 0.7f)
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial.toString(),
            fontSize = 48.sp,
            color = Color.White, // Or choose contrasting color
            fontWeight = FontWeight.Bold
        )
    }
}