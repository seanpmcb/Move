package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseSet
import com.seanpmcb.move.timer.WorkoutTimer
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.style.TextAlign
import android.view.WindowManager
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

@Composable
fun WorkoutPlayerScreen(
    workout: Workout,
    onWorkoutComplete: () -> Unit
) {
    val context = LocalContext.current
    val workoutTimer = remember { WorkoutTimer(context) }
    var currentSetIndex by remember { mutableStateOf(0) }
    var currentExerciseIndex by remember { mutableStateOf(0) }
    var currentRepetition by remember { mutableStateOf(1) }
    var timeRemaining by remember { mutableStateOf(0) }
    val isPaused by workoutTimer.isPaused().collectAsState(initial = false)
    var restartTrigger by remember { mutableStateOf(0) }
    var isWorkoutComplete by remember { mutableStateOf(false) }
    var isRestBetweenRepetitions by remember { mutableStateOf(false) }

    // Derive current set and exercise from the indices
    val currentSet by remember(currentSetIndex) {
        derivedStateOf { workout.exerciseSets[currentSetIndex] }
    }
    
    val currentExercise by remember(currentSetIndex, currentExerciseIndex) {
        derivedStateOf { currentSet.exercises[currentExerciseIndex] }
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
        for (setIndex in currentSetIndex until workout.exerciseSets.size) {
            val set = workout.exerciseSets[setIndex]
            
            for (rep in currentRepetition..set.repetitions) {
                // Update current repetition
                currentRepetition = rep
                
                // Run through all exercises in the set
                for (exIndex in (if (rep == currentRepetition) currentExerciseIndex else 0) until set.exercises.size) {
                    currentExerciseIndex = exIndex
                    
                    workoutTimer.startExerciseTimer(
                        exercise = set.exercises[exIndex],
                        isFirstExercise = setIndex == 0 && rep == 1 && exIndex == 0
                    ).collect { time ->
                        timeRemaining = time
                        if (time == 0) {
                            // Move to next exercise or repetition or set
                            if (exIndex < set.exercises.size - 1) {
                                // Move to next exercise in the set
                                currentExerciseIndex = exIndex + 1
                            } else if (rep < set.repetitions) {
                                // End of set's exercises, but more repetitions to go
                                // Add rest between repetitions if specified
                                if (set.restBetweenRepetitions > 0) {
                                    isRestBetweenRepetitions = true
                                    val restExercise = Exercise(
                                        id = "rest-between-reps",
                                        name = "Rest Before Next Set Repetition",
                                        duration = set.restBetweenRepetitions,
                                        type = ExerciseType.REST,
                                        instructions = "Rest before starting repetition ${rep + 1} of ${set.repetitions}"
                                    )
                                    
                                    workoutTimer.startExerciseTimer(
                                        exercise = restExercise,
                                        isFirstExercise = false
                                    ).collect { restTime ->
                                        timeRemaining = restTime
                                        if (restTime == 0) {
                                            isRestBetweenRepetitions = false
                                            // Reset to first exercise for next repetition
                                            currentExerciseIndex = 0
                                            currentRepetition = rep + 1
                                        }
                                    }
                                } else {
                                    // No rest, just move to next repetition
                                    currentExerciseIndex = 0
                                    currentRepetition = rep + 1
                                }
                            } else if (setIndex < workout.exerciseSets.size - 1) {
                                // End of set and all repetitions, move to next set
                                currentSetIndex = setIndex + 1
                                currentExerciseIndex = 0
                                currentRepetition = 1
                            } else {
                                // End of workout
                                workoutTimer.playWorkoutCompleteSound()
                                isWorkoutComplete = true
                            }
                        }
                    }
                }
            }
        }
    }

    suspend fun restartCurrentExercise() {
        // Start the current exercise timer with a countdown
        workoutTimer.startExerciseTimer(
            exercise = currentSet.exercises[currentExerciseIndex],
            withCountdown = true
        ).collect { time ->
            timeRemaining = time
            if (time == 0) {
                // When exercise completes, continue with the rest of the workout
                if (currentExerciseIndex < currentSet.exercises.size - 1) {
                    // Move to next exercise in the current set
                    currentExerciseIndex++
                    startWorkout()
                } else if (currentRepetition < currentSet.repetitions) {
                    // Move to next repetition of the current set
                    if (currentSet.restBetweenRepetitions > 0) {
                        isRestBetweenRepetitions = true
                        val restExercise = Exercise(
                            id = "rest-between-reps",
                            name = "Rest Before Next Set Repetition",
                            duration = currentSet.restBetweenRepetitions,
                            type = ExerciseType.REST,
                            instructions = "Rest before starting repetition ${currentRepetition + 1} of ${currentSet.repetitions}"
                        )
                        
                        workoutTimer.startExerciseTimer(
                            exercise = restExercise,
                            isFirstExercise = false
                        ).collect { restTime ->
                            timeRemaining = restTime
                            if (restTime == 0) {
                                isRestBetweenRepetitions = false
                                // Reset to first exercise for next repetition
                                currentExerciseIndex = 0
                                currentRepetition++
                                startWorkout()
                            }
                        }
                    } else {
                        // No rest, just move to next repetition
                        currentExerciseIndex = 0
                        currentRepetition++
                        startWorkout()
                    }
                } else if (currentSetIndex < workout.exerciseSets.size - 1) {
                    // Move to next set
                    currentSetIndex++
                    currentExerciseIndex = 0
                    currentRepetition = 1
                    startWorkout()
                } else {
                    // End of workout
                    workoutTimer.playWorkoutCompleteSound()
                    isWorkoutComplete = true
                }
            }
        }
    }

    LaunchedEffect(restartTrigger) {
        if (restartTrigger == 0) {
            currentSetIndex = 0
            currentExerciseIndex = 0
            currentRepetition = 1
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
            // Progress indicator
            val totalExercises = workout.exerciseSets.sumOf { set -> 
                set.exercises.count { it.type == ExerciseType.WORK } * set.repetitions 
            }
            
            val currentProgress = workout.exerciseSets.take(currentSetIndex).sumOf { set ->
                set.exercises.count { it.type == ExerciseType.WORK } * set.repetitions
            } + (if (currentSetIndex < workout.exerciseSets.size) {
                (currentRepetition - 1) * currentSet.exercises.count { it.type == ExerciseType.WORK } +
                currentSet.exercises.take(currentExerciseIndex).count { it.type == ExerciseType.WORK }
            } else 0)
            
            Text(
                text = if (isRestBetweenRepetitions) {
                    "Set ${currentSetIndex + 1}/${workout.exerciseSets.size} • Rest between repetitions"
                } else {
                    "Set ${currentSetIndex + 1}/${workout.exerciseSets.size} • Rep ${currentRepetition}/${currentSet.repetitions}"
                },
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "${currentProgress + 1}/$totalExercises exercises",
                style = MaterialTheme.typography.bodyMedium.copy(
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
                    text = if (isRestBetweenRepetitions) {
                        "Rest Before Next Repetition"
                    } else {
                        currentExercise.name
                    },
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(2.dp))
                
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = if (isRestBetweenRepetitions) {
                            "Rest before starting repetition ${currentRepetition + 1} of ${currentSet.repetitions}"
                        } else if (currentExercise.type == ExerciseType.REST) {
                            // Show next exercise name during rest
                            if (currentExerciseIndex < currentSet.exercises.size - 1) {
                                var nextIndex = currentExerciseIndex + 1
                                var nextExercise = currentSet.exercises[nextIndex]
                                while (nextExercise.type == ExerciseType.REST && nextIndex < currentSet.exercises.size - 1) {
                                    nextIndex++
                                    nextExercise = currentSet.exercises[nextIndex]
                                }
                                if (nextExercise.type == ExerciseType.REST) {
                                    if (currentRepetition < currentSet.repetitions) {
                                        "Next: Repetition ${currentRepetition + 1}"
                                    } else if (currentSetIndex < workout.exerciseSets.size - 1) {
                                        "Next: Set ${currentSetIndex + 2}"
                                    } else {
                                        "Workout Complete"
                                    }
                                } else {
                                    "Next: ${nextExercise.name}"
                                }
                            } else if (currentRepetition < currentSet.repetitions) {
                                "Next: Repetition ${currentRepetition + 1}"
                            } else if (currentSetIndex < workout.exerciseSets.size - 1) {
                                "Next: Set ${currentSetIndex + 2}"
                            } else {
                                "Workout Complete"
                            }
                        } else {
                            currentExercise.instructions
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Image section
                if (!isRestBetweenRepetitions) {
                    currentExercise.imageResId?.let { resId ->
                        Surface(
                            modifier = Modifier
                                .size(300.dp)
                                .padding(vertical = 16.dp),
                            shape = MaterialTheme.shapes.large,
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
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = if (timeRemaining < 0) "${-timeRemaining}" else "$timeRemaining",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = if (timeRemaining <= 3) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    )
                )
            }

            // Next exercise section (now at bottom)
            if (!isRestBetweenRepetitions && (currentExercise.type == ExerciseType.WORK || currentExercise.type == ExerciseType.REST)) {
                Surface(
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
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next Exercise",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Text(
                            text = if (currentExerciseIndex < currentSet.exercises.size - 1) {
                                var nextIndex = currentExerciseIndex + 1
                                var nextExercise = currentSet.exercises[nextIndex]
                                while (nextExercise.type == ExerciseType.REST && nextIndex < currentSet.exercises.size - 1) {
                                    nextIndex++
                                    nextExercise = currentSet.exercises[nextIndex]
                                }
                                if (nextExercise.type == ExerciseType.REST) {
                                    if (currentRepetition < currentSet.repetitions) {
                                        "Next: Repetition ${currentRepetition + 1}"
                                    } else if (currentSetIndex < workout.exerciseSets.size - 1) {
                                        "Next: Set ${currentSetIndex + 2}"
                                    } else {
                                        "Workout Complete"
                                    }
                                } else {
                                    nextExercise.name
                                }
                            } else if (currentRepetition < currentSet.repetitions) {
                                "Next: Repetition ${currentRepetition + 1}"
                            } else if (currentSetIndex < workout.exerciseSets.size - 1) {
                                "Next: Set ${currentSetIndex + 2}"
                            } else {
                                "Workout Complete"
                            },
                            style = MaterialTheme.typography.titleLarge,
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