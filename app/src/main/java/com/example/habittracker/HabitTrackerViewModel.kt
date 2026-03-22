package com.example.habittracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.habittracker.model.Habit
import java.time.LocalDate
import java.util.UUID

class HabitTrackerViewModel : ViewModel() {
    private val _habits = mutableStateListOf<Habit>()

    val habits : List<Habit>
        get() = _habits

    val currentDate: LocalDate = LocalDate.now()

    /**
     * Adds a new habit to the saved list of habits.
     */
    fun addHabit(habitName: String) {
        val newHabit = Habit(
            id = UUID.randomUUID().toString(),
            name = habitName
        )
        _habits.add(newHabit)
    }

    /**
     * Updates the value of [Habit.completed].
     */
    fun checkHabit(habit: Habit) {
        val index = _habits.indexOf(habit)
        _habits[index] = habit.copy(completed = !habit.completed)
    }

    /**
     * Removes a habit from the saved list of habits.
     */
    fun removeHabit(habit: Habit) {
        _habits.remove(habit)
    }

    /**
     * Removes all existing habits.
     */
    fun clearHabits() {
        _habits.clear()
    }
}