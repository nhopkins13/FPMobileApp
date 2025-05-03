package com.csci448.fpmobileapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class) // For FlowRow and SegmentedButton
@Composable
fun SettingScreen(
    viewModel: StudySaurusVM,
    modifier: Modifier = Modifier
) {
    val currentNavBarColorKey by viewModel.navBarColorKey.collectAsState()
    val currentThemeKey by viewModel.appThemeKey.collectAsState()
    val selectableNavBarColors = viewModel.selectableNavBarColors
    val themeOptions = listOf("Light", "Dark", "System") // Must match keys used in VM/MainActivity

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 24.dp))

        // --- Nav Bar Color Setting ---
        Text("Navigation Bar Color", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        // Use FlowRow for wrapping if many colors, or simple Row if few
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectableNavBarColors.forEach { (key, color) ->
                ColorChip(
                    color = color,
                    label = key,
                    isSelected = key == currentNavBarColorKey,
                    onClick = { viewModel.updateNavBarColor(key) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- App Theme Setting ---
        Text("App Theme", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            themeOptions.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = themeOptions.size),
                    onClick = { viewModel.updateAppTheme(label) },
                    selected = label == currentThemeKey
                ) {
                    Text(label)
                }
            }
        }

        // Add other settings here...

    }
}

@Composable
fun ColorChip(
    color: Color,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 40.dp) // Adjust size
            .clip(MaterialTheme.shapes.small)
            .background(color)
            .clickable(onClick = onClick)
            .border(
                width = if (isSelected) 2.dp else Dp.Hairline,
                color = if (isSelected) MaterialTheme.colorScheme.outline else Color.Gray, // Highlight if selected
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ) {
        // Optional: Show label on chip if desired, ensure contrast
        // Text(label, color = if (isSelected) Color.White else Color.Black )
    }
}