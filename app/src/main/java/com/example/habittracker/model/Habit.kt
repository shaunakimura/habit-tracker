package com.example.habittracker.model

/**
 * Represents a habit.
 * @property id A unique ID to identify the habit.
 * @property name The name of the habit.
 * @property completed A mutable state representing whether the habit has been completed or not.
 */
data class Habit(
    val id: String,
    val name: String,
    var completed: Boolean = false,
)