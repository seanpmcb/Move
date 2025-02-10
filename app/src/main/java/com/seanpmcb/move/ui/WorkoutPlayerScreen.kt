package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.timer.WorkoutTimer
import kotlinx.coroutines.flow.collect

@Composable
fun WorkoutPlayerScreen(
    workout: Workout,
    onWorkoutComplete: () -> Unit
) {
    val context = LocalContext.current
    val workoutTimer = remember { WorkoutTimer(context) }
    var currentExerciseIndex by remember { mutableStateOf(0) }
    var timeRemaining by remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        onDispose {
            workoutTimer.release()
        }
    }

    LaunchedEffect(currentExerciseIndex) {
        val currentExercise = workout.exercises[currentExerciseIndex]
        workoutTimer.startExerciseTimer(currentExercise).collect { time ->
            timeRemaining = time
            if (time == 0) {
                if (currentExerciseIndex < workout.exercises.size - 1) {
                    currentExerciseIndex++
                } else {
                    // Workout is complete
                    onWorkoutComplete()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentExercise = workout.exercises[currentExerciseIndex]
        
        Text(
            text = currentExercise.name,
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = currentExercise.instructions,
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = if (timeRemaining < 0) {
                "Starting in ${-timeRemaining}..."
            } else {
                "$timeRemaining"
            },
            style = MaterialTheme.typography.displayLarge
        )
        
        if (currentExercise.type == ExerciseType.WORK) {
            Text(
                text = "Next: ${
                    if (currentExerciseIndex < workout.exercises.size - 1) 
                        workout.exercises[currentExerciseIndex + 1].name 
                    else 
                        "Workout Complete"
                }",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
} 