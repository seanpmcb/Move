package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.MeasurementType
import com.seanpmcb.move.data.Workout

// Helper class for workout preview to represent a set of exercises
private data class ExerciseSet(
    val name: String,
    val exercises: List<Exercise>,
    val setCount: Int = 1,
    val isSuperset: Boolean = exercises.size > 1,
    val isSet: Boolean = false // Flag to indicate if this is an actual set or just a standalone exercise
)

// Find patterns in exercise instructions to identify sets
private fun groupExercisesIntoSets(exercises: List<Exercise>): List<ExerciseSet> {
    val workExercises = exercises.filter { it.type == ExerciseType.WORK }
    val sets = mutableListOf<ExerciseSet>()
    
    // Track which exercises we've processed
    val processedIndices = mutableSetOf<Int>()
    
    // First pass: Find supersets (exercises with same set index in instructions)
    for (i in workExercises.indices) {
        if (i in processedIndices) continue
        
        val currentExercise = workExercises[i]
        val setRegex = "Set (\\d+)/(\\d+):".toRegex()
        val matchResult = currentExercise.instructions?.let { setRegex.find(it) }
        
        if (matchResult != null) {
            // This is part of a set
            val setNumber = matchResult.groupValues[1].toInt()
            val totalSets = matchResult.groupValues[2].toInt()
            
            // Find all exercises in this set
            val currentSetExercises = mutableListOf<Exercise>()
            currentSetExercises.add(currentExercise)
            processedIndices.add(i)
            
            // Check if there are paired exercises (superset)
            var j = i + 1
            while (j < workExercises.size) {
                val nextExercise = workExercises[j]
                val nextMatchResult = nextExercise.instructions?.let { setRegex.find(it) }
                
                if (nextMatchResult != null && 
                    nextMatchResult.groupValues[1].toInt() == setNumber && 
                    nextMatchResult.groupValues[2].toInt() == totalSets) {
                    // This is part of the same set
                    currentSetExercises.add(nextExercise)
                    processedIndices.add(j)
                    j++
                } else {
                    // Different set
                    break
                }
            }
            
            // Check if we have multiple exercises with the same set number
            // If so, they're part of a superset for a single set
            if (setNumber == 1) {
                val setName = if (currentSetExercises.size > 1) {
                    "Superset: ${currentSetExercises.joinToString(" + ") { it.name }}"
                } else {
                    currentSetExercises.first().name
                }
                
                sets.add(ExerciseSet(
                    name = setName,
                    exercises = currentSetExercises,
                    setCount = totalSets,
                    isSuperset = currentSetExercises.size > 1,
                    isSet = true
                ))
            }
        } else {
            // This is a standalone exercise
            processedIndices.add(i)
            sets.add(ExerciseSet(
                name = currentExercise.name,
                exercises = listOf(currentExercise),
                isSet = false
            ))
        }
    }
    
    // Second pass: Add any exercises we missed
    for (i in workExercises.indices) {
        if (i !in processedIndices) {
            sets.add(ExerciseSet(
                name = workExercises[i].name,
                exercises = listOf(workExercises[i]),
                isSet = false
            ))
        }
    }
    
    return sets
}

@Composable
fun WorkoutPreviewScreen(
    workout: Workout,
    onStartWorkout: () -> Unit,
    onBackClick: () -> Unit
) {
    // Group exercises into sets
    val exerciseSets = remember(workout) {
        groupExercisesIntoSets(workout.exercises)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = workout.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = workout.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Only show duration for time-based workouts
        if (workout.totalDuration != null && workout.totalDuration > 0) {
            Text(
                text = "Total Duration: ${(workout.totalDuration / 60)}m ${(workout.totalDuration % 60)}s",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(exerciseSets) { exerciseSet ->
                if (exerciseSet.isSet) {
                    // This is an actual set
                    ExerciseSetPreviewItem(exerciseSet = exerciseSet)
                } else {
                    // This is a standalone exercise
                    exerciseSet.exercises.forEach { exercise ->
                        StandaloneExercisePreviewItem(exercise = exercise)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = onBackClick) {
                Text("Back")
            }
            
            Button(
                onClick = onStartWorkout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Text("Start Workout")
            }
        }
    }
}

@Composable
private fun ExerciseSetPreviewItem(exerciseSet: ExerciseSet) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            // Set title with set count
            Text(
                text = if (exerciseSet.setCount > 1) {
                    "${exerciseSet.name} (${exerciseSet.setCount} sets)"
                } else {
                    exerciseSet.name
                },
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // List all exercises in the set
            exerciseSet.exercises.forEach { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    when (exercise.measurementType) {
                        null -> {
                            exercise.duration?.let { duration ->
                                Text(
                                    text = "${duration}s",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        MeasurementType.REPS -> {
                            exercise.repetitions?.let { reps ->
                                Text(
                                    text = "$reps reps${exercise.weight?.let { " at $it" } ?: ""}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        MeasurementType.CUSTOM -> {
                            exercise.customMeasurement?.let { measurement ->
                                Text(
                                    text = measurement + (exercise.weight?.let { " at $it" } ?: ""),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StandaloneExercisePreviewItem(exercise: Exercise) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.bodyMedium
            )
            
            when (exercise.measurementType) {
                null -> {
                    exercise.duration?.let { duration ->
                        Text(
                            text = "${duration}s",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                MeasurementType.REPS -> {
                    exercise.repetitions?.let { reps ->
                        Text(
                            text = "$reps reps${exercise.weight?.let { " at $it" } ?: ""}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                MeasurementType.CUSTOM -> {
                    exercise.customMeasurement?.let { measurement ->
                        Text(
                            text = measurement + (exercise.weight?.let { " at $it" } ?: ""),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
} 