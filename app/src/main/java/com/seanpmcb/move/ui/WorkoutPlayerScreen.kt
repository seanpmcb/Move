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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward

@Composable
fun WorkoutPlayerScreen(
    workout: Workout,
    onWorkoutComplete: () -> Unit
) {
    val context = LocalContext.current
    val workoutTimer = remember { WorkoutTimer(context) }
    var currentExerciseIndex by remember { mutableStateOf(0) }
    var timeRemaining by remember { mutableStateOf(0) }
    val isPaused by workoutTimer.isPaused().collectAsState(initial = false)
    var restartTrigger by remember { mutableStateOf(0) }
    var isWorkoutComplete by remember { mutableStateOf(false) }

    // Derive current exercise from the index
    val currentExercise by remember(currentExerciseIndex) {
        derivedStateOf { workout.exercises[currentExerciseIndex] }
    }

    suspend fun startWorkout() {
        for (i in currentExerciseIndex until workout.exercises.size) {
            workoutTimer.startExerciseTimer(
                exercise = workout.exercises[i],
                isFirstExercise = i == 0
            ).collect { time ->
                timeRemaining = time
                if (time == 0) {
                    if (i == workout.exercises.size - 1) {
                        workoutTimer.playWorkoutCompleteSound()
                        isWorkoutComplete = true
                    } else {
                        currentExerciseIndex = i + 1
                    }
                }
            }
        }
    }

    suspend fun restartCurrentExercise() {
        workoutTimer.restartCurrentExercise()?.collect { time ->
            timeRemaining = time
        }
    }

    LaunchedEffect(restartTrigger) {
        if (restartTrigger == 0) {
            currentExerciseIndex = 0
            startWorkout()
        } else {
            restartCurrentExercise()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            workoutTimer.release()
        }
    }

    if (isWorkoutComplete) {
        WorkoutCompleteScreen(
            workout = workout,
            onDismiss = onWorkoutComplete
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${currentExerciseIndex + 1}/${workout.exercises.size}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

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
                text = if (timeRemaining < 0) "Get Ready: ${-timeRemaining}" else "$timeRemaining",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Control buttons row
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilledTonalButton(
                    onClick = { workoutTimer.togglePause() }
                ) {
                    Text(if (isPaused) "Resume" else "Pause")
                }

                FilledTonalButton(
                    onClick = { restartTrigger++ }
                ) {
                    Text("Restart Exercise")
                }
            }
            
            if (currentExercise.type == ExerciseType.WORK || currentExercise.type == ExerciseType.REST) {
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "NEXT",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (currentExerciseIndex < workout.exercises.size - 1) {
                                var nextIndex = currentExerciseIndex + 1
                                var nextExercise = workout.exercises[nextIndex]
                                while (nextExercise.type == ExerciseType.REST && nextIndex < workout.exercises.size - 1) {
                                    nextIndex++
                                    nextExercise = workout.exercises[nextIndex]
                                }
                                if (nextExercise.type == ExerciseType.REST) "Workout Complete" else nextExercise.name
                            } else {
                                "Workout Complete"
                            },
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
} 