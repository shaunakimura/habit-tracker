package com.example.habittracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.habittracker.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.util.UUID

data class HabitUiState(
    val habits: List<Habit> = emptyList()
)

class HabitTrackerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HabitUiState())
    val uiState = _uiState.asStateFlow()

    val currentDate: LocalDate
        get() = LocalDate.now()

    /**
     * Adds a new habit to the saved list of habits.
     */
    fun addHabit(habitName: String) {
        // Verify habit name is not blank
        if (habitName.isBlank()) return

        // Create new habit
        val newHabit = Habit(
            id = UUID.randomUUID().toString(),
            name = habitName
        )

        _uiState.value = _uiState.value.copy(
            habits = _uiState.value.habits + newHabit
        )
    }

    /**
     * Updates the value of [Habit.completed].
     */
    fun checkHabit(habit: Habit) {
        _uiState.value = _uiState.value.copy(
            habits = _uiState.value.habits.map {
                if (it.id == habit.id) {
                    it.copy(completed = !it.completed)
                } else {
                    it
                }
            }
        )
    }


    /**
     * Removes a habit from the saved list of habits.
     */
    fun removeHabit(habit: Habit) {
        _uiState.value = _uiState.value.copy(
            habits = _uiState.value.habits.filter {
                it.id != habit.id
            }
        )
    }

    /**
     * Removes all existing habits.
     */
    fun clearHabits() {
        _uiState.value = _uiState.value.copy(
            habits = emptyList()
        )
    }
}