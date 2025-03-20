package com.seanpmcb.move.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorkoutRepository(private val context: Context) {
    private val yamlParser = YamlParser(context)

    fun getWorkoutGroups(): Flow<List<WorkoutGroup>> = flow {
        val groups = yamlParser.loadWorkoutGroups()
        emit(groups)
    }

    fun getWorkout(workoutId: String): Flow<Workout?> = flow {
        val groups = yamlParser.loadWorkoutGroups()
        val workoutReference = groups
            .flatMap { it.workouts }
            .find { it.id == workoutId }

        if (workoutReference != null) {
            val workout = yamlParser.loadWorkout(workoutReference.file)
            emit(workout)
        } else {
            emit(null)
        }
    }

    suspend fun updateExerciseWeight(workoutId: String, exerciseIndex: Int, newWeight: Int) {
        val groups = yamlParser.loadWorkoutGroups()
        val workoutReference = groups
            .flatMap { it.workouts }
            .find { it.id == workoutId }

        workoutReference?.let { ref ->
            yamlParser.updateExerciseWeight(ref.file, exerciseIndex, newWeight)
        }
    }
}