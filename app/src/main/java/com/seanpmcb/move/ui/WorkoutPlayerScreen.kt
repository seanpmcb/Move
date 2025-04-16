package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.MeasurementType
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.timer.WorkoutTimer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.ui.text.style.TextAlign
import android.view.WindowManager
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WorkoutPlayerScreen(
    workout: Workout,
    onWorkoutComplete: () -> Unit,
    onWeightUpdate: (exerciseIndex: Int, newWeight: Int) -> Unit
) {
    val context = LocalContext.current
    val workoutTimer = remember { WorkoutTimer(context) }
    var currentExerciseIndex by remember { mutableIntStateOf(0) }
    var timeRemaining by remember { mutableIntStateOf(0) }
    val isPaused by workoutTimer.isPaused().collectAsState(initial = false)
    var restartTrigger by remember { mutableIntStateOf(0) }
    var isWorkoutComplete by remember { mutableStateOf(false) }
    var manualProgressionMode by remember { mutableStateOf(false) }
    var showWeightDialog by remember { mutableStateOf(false) }
    var currentWeight by remember { mutableStateOf("") }

    // Derive current exercise from the index
    val currentExercise by remember(currentExerciseIndex) {
        derivedStateOf { workout.exercises[currentExerciseIndex] }
    }
    
    // Calculate work exercise indices and count
    val workExerciseIndices by remember(workout) {
        derivedStateOf {
            workout.exercises.mapIndexedNotNull { index, exercise ->
                if (exercise.type == ExerciseType.WORK) index else null
            }
        }
    }
    
    // Find the current work exercise index
    val currentWorkExerciseIndex by remember(currentExerciseIndex, workExerciseIndices) {
        derivedStateOf {
            workExerciseIndices.indexOfFirst { it >= currentExerciseIndex }
        }
    }
    
    // Calculate total work exercises
    val totalWorkExercises by remember(workExerciseIndices) {
        derivedStateOf { workExerciseIndices.size }
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
        // Starting a new exercise
        val currentExercise = workout.exercises[currentExerciseIndex]
        
        // Only proceed with timer for TIME-based exercises
        // For REPS or CUSTOM, we'll let the user progress manually
        if (currentExercise.measurementType == null) {
            manualProgressionMode = false
            workoutTimer.startExerciseTimer(
                exercise = currentExercise,
                isFirstExercise = currentExerciseIndex == 0
            ).collect { time ->
                timeRemaining = time
                if (time == 0) {
                    // Auto progress to next exercise when timer ends
                    if (currentExerciseIndex < workout.exercises.size - 1) {
                        currentExerciseIndex++
                        startWorkout()
                    } else {
                        workoutTimer.playWorkoutCompleteSound()
                        isWorkoutComplete = true
                    }
                }
            }
        } else {
            // For rep-based or custom exercises, just set manual mode
            // The user will tap the Complete Exercise button
            manualProgressionMode = true
            workoutTimer.startExerciseTimer(
                exercise = currentExercise,
                isFirstExercise = currentExerciseIndex == 0
            ).collect { time ->
                // We just need to collect this to display the exercise
                timeRemaining = time
            }
        }
    }

    suspend fun restartCurrentExercise() {
        val currentExercise = workout.exercises[currentExerciseIndex]
        
        // Only use timer for TIME-based exercises
        if (currentExercise.measurementType == null) {
            manualProgressionMode = false
            // Start the current exercise timer with a countdown
            workoutTimer.startExerciseTimer(
                exercise = currentExercise,
                withCountdown = true
            ).collect { time ->
                timeRemaining = time
                if (time == 0) {
                    // When exercise completes, continue with the rest of the workout
                    if (currentExerciseIndex < workout.exercises.size - 1) {
                        currentExerciseIndex++
                        startWorkout()
                    } else {
                        workoutTimer.playWorkoutCompleteSound()
                        isWorkoutComplete = true
                    }
                }
            }
        } else {
            // For non-timed exercises, just show them
            manualProgressionMode = true
            workoutTimer.startExerciseTimer(
                exercise = currentExercise,
                withCountdown = true
            ).collect { time ->
                timeRemaining = time
            }
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

    // When current exercise index changes, start the next exercise
    LaunchedEffect(currentExerciseIndex) {
        if (restartTrigger != 0 || currentExerciseIndex == 0) return@LaunchedEffect
        
        // Extra precaution to ensure no multiple timers
        workoutTimer.cancelCurrentTimer()
        startWorkout()
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
                .systemBarsPadding()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress bar with exercise counter text inside - only counting WORK exercises
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                LinearProgressIndicator(
                    progress = if (totalWorkExercises > 0) {
                        (currentWorkExerciseIndex + 1).toFloat() / totalWorkExercises
                    } else {
                        0f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primaryContainer
                )
                
                // Center the text inside the progress bar with improved readability
                Surface(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = if (totalWorkExercises > 0) {
                            "${currentWorkExerciseIndex + 1}/${totalWorkExercises}"
                        } else {
                            "${currentExerciseIndex + 1}/${workout.exercises.size}"
                        },
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

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
                
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(120.dp)
                        .padding(vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (currentExercise.type == ExerciseType.REST) {
                                // Show next exercise name during rest
                                if (currentExerciseIndex < workout.exercises.size - 1) {
                                    var nextIndex = currentExerciseIndex + 1
                                    var nextExercise = workout.exercises[nextIndex]
                                    while (nextExercise.type == ExerciseType.REST && nextIndex < workout.exercises.size - 1) {
                                        nextIndex++
                                        nextExercise = workout.exercises[nextIndex]
                                    }
                                    if (nextExercise.type == ExerciseType.REST) {
                                        "Workout Complete"
                                    } else {
                                        "Next: ${nextExercise.name}"
                                    }
                                } else {
                                    "Workout Complete"
                                }
                            } else {
                                currentExercise.instructions ?: "No instructions available"
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                // Image section
                currentExercise.imageResId?.let { resId ->
                    val resourceId = context.resources.getIdentifier(resId, "drawable", context.packageName)
                    if (resourceId != 0) {
                        Surface(
                            modifier = Modifier
                                .size(300.dp)
                                .padding(vertical = 8.dp),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "Exercise: ${currentExercise.name}",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        Surface(
                            modifier = Modifier
                                .size(300.dp)
                                .padding(vertical = 8.dp),
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
                } ?: Surface(
                    modifier = Modifier
                        .size(300.dp)
                        .padding(vertical = 8.dp),
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
                
                // Only show timer for time-based exercises
                if (currentExercise.measurementType == null) {
                    Text(
                        text = if (timeRemaining < 0) "${-timeRemaining}" else "$timeRemaining",
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = if (timeRemaining <= 3) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary
                        )
                    )
                } else {
                    // For rep-based exercises, show reps
                    when (currentExercise.measurementType) {
                        MeasurementType.REPS -> {
                            Text(
                                text = "${currentExercise.repetitions} reps",
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            if (currentExercise.weight != null) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.clickable { 
                                        currentWeight = currentExercise.weight.toString()
                                        showWeightDialog = true 
                                    }
                                ) {
                                    Text(
                                        text = "${currentExercise.weight} lbs",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "Edit weight",
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(24.dp),
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }
                        }
                        MeasurementType.CUSTOM -> {
                            Text(
                                text = currentExercise.customMeasurement ?: "",
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            if (currentExercise.weight != null) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.clickable { 
                                        currentWeight = currentExercise.weight.toString()
                                        showWeightDialog = true 
                                    }
                                ) {
                                    Text(
                                        text = "${currentExercise.weight} lbs",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "Edit weight",
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(24.dp),
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }
                        }
                        null -> {}
                    }
                    
                    // Add "Complete" button for non-timed exercises
                    Button(
                        onClick = {
                            if (currentExerciseIndex < workout.exercises.size - 1) {
                                // Cancel the current timer to avoid having multiple timers
                                workoutTimer.cancelCurrentTimer()
                                
                                // Immediately update the timeRemaining for visual feedback
                                val nextExercise = workout.exercises[currentExerciseIndex + 1]
                                if (nextExercise.measurementType == null) {
                                    nextExercise.duration?.let { duration ->
                                        timeRemaining = duration
                                    }
                                }
                                
                                currentExerciseIndex++
                            } else {
                                // Cancel any existing timer
                                workoutTimer.cancelCurrentTimer()
                                // Launch a coroutine to play the sound and complete the workout
                                CoroutineScope(Dispatchers.Main).launch {
                                    workoutTimer.playWorkoutCompleteSound()
                                    isWorkoutComplete = true
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(0.7f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(if (currentExerciseIndex == workout.exercises.size - 1) "Complete Workout" else "Complete Exercise")
                    }
                }
            }

            // Next exercise section (now at bottom)
            if (currentExercise.type == ExerciseType.WORK || currentExercise.type == ExerciseType.REST) {
                Surface(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            // Skip to next exercise when clicked
                            if (currentExerciseIndex < workout.exercises.size - 1) {
                                // Cancel the current timer to avoid having multiple timers
                                workoutTimer.cancelCurrentTimer()
                                
                                // Immediately update the timeRemaining for visual feedback
                                val nextExercise = workout.exercises[currentExerciseIndex + 1]
                                if (nextExercise.measurementType == null) {
                                    nextExercise.duration?.let { duration ->
                                        timeRemaining = duration
                                    }
                                }
                                
                                // Update exercise index which will trigger the LaunchedEffect to start the next exercise
                                currentExerciseIndex++
                            } else {
                                // Cancel any existing timer
                                workoutTimer.cancelCurrentTimer()
                                // Launch a coroutine to play the sound and complete the workout
                                CoroutineScope(Dispatchers.Main).launch {
                                    workoutTimer.playWorkoutCompleteSound()
                                    isWorkoutComplete = true
                                }
                            }
                        },
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

    if (showWeightDialog) {
        AlertDialog(
            onDismissRequest = { showWeightDialog = false },
            title = { Text("Edit Weight") },
            text = {
                OutlinedTextField(
                    value = currentWeight,
                    onValueChange = { newValue ->
                        // Only allow numbers
                        if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                            currentWeight = newValue
                        }
                    },
                    label = { Text("Weight (lbs)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        currentWeight.toIntOrNull()?.let { weight ->
                            onWeightUpdate(currentExerciseIndex, weight)
                            // Update the workout object to reflect the change immediately
                            workout.exercises[currentExerciseIndex].weight = weight
                        }
                        showWeightDialog = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showWeightDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
} 