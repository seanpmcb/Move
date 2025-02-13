package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
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
import androidx.compose.foundation.Image
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

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
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${currentExerciseIndex + 1}/${workout.exercises.size}",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Center section with exercise info, image, and timer
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentExercise.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(2.dp))
                
                Surface(
//                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = currentExercise.instructions,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Image section
                currentExercise.imageResId?.let { resId ->
                    Surface(
                        modifier = Modifier
                            .size(300.dp)
                            .padding(vertical = 16.dp),
                        shape = MaterialTheme.shapes.large,
//                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = "Exercise: ${currentExercise.name}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                } ?: Surface(
                    modifier = Modifier
                        .size(300.dp)
                        .padding(vertical = 16.dp),
                    shape = MaterialTheme.shapes.medium,
//                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Exercise\nImage",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = if (timeRemaining < 0) "${-timeRemaining}" else "$timeRemaining",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = if (timeRemaining <= 3) MaterialTheme.colorScheme.primary 
                        else MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            // Next exercise section (now at bottom)
            if (currentExercise.type == ExerciseType.WORK || currentExercise.type == ExerciseType.REST) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
//                        Text(
//                            text = "NEXT",
//                            style = MaterialTheme.typography.labelLarge,
//                            color = MaterialTheme.colorScheme.onPrimaryContainer
//                        )

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next Exercise",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
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
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Control buttons row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilledTonalButton(
                    onClick = { workoutTimer.togglePause() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(if (isPaused) "Resume" else "Pause")
                }

                FilledTonalButton(
                    onClick = { restartTrigger++ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text("Restart Exercise")
                }
            }
        }
    }
} 