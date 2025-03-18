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

enum class ExerciseMeasurementType {
    TIME,    // Exercise is measured in seconds
    REPS,    // Exercise is measured in repetitions
    CUSTOM   // Exercise has custom measurement (e.g., "3 rounds")
}

data class Exercise(
    val id: String,
    val name: String,
    val duration: Int = 0, // in seconds, used only for TIME measurement type
    val type: ExerciseType,
    val instructions: String = "",
    val preparationDuration: Int = if (type == ExerciseType.WORK) 3 else 0,
    val imageResId: Int? = null,
    val measurementType: ExerciseMeasurementType = ExerciseMeasurementType.TIME,
    val repetitions: Int = 0,        // Number of repetitions for REPS measurement type
    val weight: String = "",         // Weight for strength exercises (e.g., "25 lbs")
    val customMeasurement: String = "" // Custom measurement description
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