package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.HabitTrackerViewModel
import com.example.habittracker.R
import com.example.habittracker.model.Habit
import com.example.habittracker.ui.theme.HabitTrackerTheme
import java.time.LocalDate

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HabitTrackerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomePageContent(
        currentDate = viewModel.currentDate,
        habits = uiState.habits,
        onAddHabit = viewModel::addHabit,
        onHabitChecked = viewModel::checkHabit,
        onRemoveHabit = viewModel::removeHabit,
        onClearAll = viewModel::clearHabits,
        modifier = modifier
    )

}

@Composable
fun HomePageContent(
    currentDate: LocalDate,
    habits: List<Habit>,
    onAddHabit: (String) -> Unit,
    onHabitChecked: (Habit) -> Unit,
    onRemoveHabit: (Habit) -> Unit,
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        DateHeader(currentDate)
        Spacer(Modifier.height(24.dp))
        HabitForm(onAddHabit)
        Spacer(Modifier.height(24.dp))
        HabitListSection(
            habits = habits,
            onClearAll = onClearAll,
            onHabitChecked = onHabitChecked,
            onRemoveHabit = onRemoveHabit
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = typography.titleLarge.copy(textAlign = TextAlign.Center),
                modifier = modifier.fillMaxWidth()
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier
    )
}

@Composable
@Preview("Home Page Content Preview", showBackground = true)
fun HomePageContentPreview() {
    HabitTrackerTheme {
        HomePageContent(
            currentDate = LocalDate.now(),
            habits = listOf(
                Habit(
                    id = "123",
                    name = "Exercise 30 min",
                    completed = false,
                )
            ),
            onAddHabit = {},
            onHabitChecked = {},
            onRemoveHabit = {},
            onClearAll = {}
        )
    }
}