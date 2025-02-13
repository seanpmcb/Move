package com.seanpmcb.move.data

data class WorkoutGroup(
    val id: String,
    val name: String,
    val workouts: List<Workout>
)

data class Workout(
    val id: String,
    val name: String,
    val description: String,
    val exercises: List<Exercise>,
    val totalDuration: Int // in seconds
)

data class Exercise(
    val id: String,
    val name: String,
    val duration: Int, // in seconds
    val type: ExerciseType,
    val instructions: String = "",
    val preparationDuration: Int = if (type == ExerciseType.WORK) 3 else 0, // 3 second countdown for work exercises
    val imageResId: Int? = null // Resource ID for the exercise image
)

enum class ExerciseType {
    WORK,
    REST,
    PREPARATION,
    TRANSITION
}

enum class TimerSound {
    COUNTDOWN_BEEP,
    START_BEEP
} 