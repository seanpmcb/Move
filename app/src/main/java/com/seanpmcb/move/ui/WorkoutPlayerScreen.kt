package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.timer.WorkoutTimer
import kotlinx.coroutines.flow.collect
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.text.style.TextAlign
import android.view.WindowManager
import android.app.Activity

@Composable
fun WorkoutPlayerScreen(
    workout: Workout,
    onWorkoutComplete: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current
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

    // Keep screen on while workout is active
    DisposableEffect(Unit) {
        val window = (context as Activity).window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
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

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (currentExercise.type == ExerciseType.WORK || currentExercise.type == ExerciseType.REST) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "NEXT",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next Exercise",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

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
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Control buttons row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
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
        }
    }
} 