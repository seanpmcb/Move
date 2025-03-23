package com.seanpmcb.move.data

data class Workout(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val exercises: List<Exercise> = emptyList()
) {
    constructor() : this("", "", "", emptyList())

    fun calculateDuration(): Int? {
        if (exercises.any { it.duration == null }) return null
        return exercises.sumOf { it.duration ?: 0 }
    }
}

data class WorkoutGroups(
    val groups: List<WorkoutGroup> = emptyList()
) {
    constructor() : this(emptyList())
}

data class WorkoutGroup(
    val id: String = "",
    val name: String = "",
    val workouts: List<WorkoutReference> = emptyList()
) {
    constructor() : this("", "", emptyList())
}

data class WorkoutReference(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val file: String = ""
) {
    constructor() : this("", "", "", "")
}

data class Exercise(
    val id: String = "",
    val name: String = "",
    val duration: Int? = null,
    val type: ExerciseType = ExerciseType.WORK,
    val measurementType: MeasurementType? = null,
    val repetitions: Int? = null,
    var weight: Int? = null,
    val customMeasurement: String? = null,
    val instructions: String? = null,
    val imageResId: String? = null
) {
    constructor() : this("", "", null, ExerciseType.WORK, null, null, null, null, null, null)
}

enum class ExerciseType {
    WORK,
    REST,
    TRANSITION
}

enum class MeasurementType {
    REPS,
    CUSTOM
}

enum class TimerSound {
    COUNTDOWN_BEEP,
    START_BEEP
} 