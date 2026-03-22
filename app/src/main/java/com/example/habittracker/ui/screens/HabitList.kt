package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.model.Habit

@Composable
fun HabitListSection(
    habits: List<Habit>,
    onClearAll: () -> Unit,
    onHabitChecked: (Habit) -> Unit,
    onRemoveHabit: (Habit) -> Unit,
) {
    HabitListHeader(onClearAll)

    Spacer(Modifier.height(4.dp))

    HabitList(
        habits = habits,
        onHabitChecked = onHabitChecked,
        onRemoveHabit = onRemoveHabit
    )


}

@Composable
fun HabitListHeader(
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.habits_header),
                style = typography.headlineSmall,
                modifier = modifier.padding(8.dp)
            )
            // Button to remove all habits
            ElevatedButton(onClick = { onClearAll() }) {
                Text(stringResource(R.string.clear_all))
            }
        }
    }
}

@Composable
fun HabitList(
    habits: List<Habit>,
    onHabitChecked: (Habit) -> Unit,
    onRemoveHabit: (Habit) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxSize()
    ) {
        items(habits) {
            HabitItem(it, onHabitChecked, onRemoveHabit)
            HorizontalDivider()
        }
    }
}

@Composable
fun HabitItem(
    habit: Habit,
    onHabitChecked: (Habit) -> Unit,
    onRemoveHabit: (Habit) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = habit.completed,
                onCheckedChange = { onHabitChecked(habit) },
            )
            Text(
                text = habit.name,
                style = if (habit.completed) {
                    typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough)
                } else {
                    typography.bodyLarge
                }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onRemoveHabit(habit) },
                modifier = modifier.size(20.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Remove habit"
                )
            }
        }
    }
}