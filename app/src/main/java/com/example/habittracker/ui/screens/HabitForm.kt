package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HabitForm(
    onAddHabit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var habitName by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Text field to enter new habit
        TextField(
            value = habitName,
            onValueChange = { newText ->
                habitName = newText
            },
            label = { Text("Habit name") },
            modifier = modifier.fillMaxWidth()
        )

        // Button to save habit
        Button(
            onClick = {
                onAddHabit(habitName)
                habitName = "" // Clear text
            },
            enabled = habitName.isNotEmpty()
        ) {
            Text(
                text = "Add habit",
                style = typography.bodyMedium.copy(textAlign = TextAlign.Center),
                modifier = modifier.fillMaxWidth()
            )
        }
    }

}
