package com.seanpmcb.move

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.data.WorkoutGroup
import com.seanpmcb.move.data.WorkoutReference
import com.seanpmcb.move.ui.theme.MoveTheme
import com.seanpmcb.move.ui.WorkoutPlayerScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.seanpmcb.move.ui.WorkoutPreviewScreen
import com.seanpmcb.move.ui.workout.WorkoutViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isPreviewMode by remember { mutableStateOf(false) }
            val viewModel: WorkoutViewModel = viewModel()
            
            // Initialize the ViewModel with the context
            LaunchedEffect(Unit) {
                viewModel.initialize(this@MainActivity)
            }
            
            // Collect state from ViewModel
            val workoutGroups by viewModel.workoutGroups.collectAsState()
            val selectedWorkout by viewModel.selectedWorkout.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val error by viewModel.error.collectAsState()
            
            MoveTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        isLoading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = androidx.compose.ui.Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        error != null -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = androidx.compose.ui.Alignment.Center
                            ) {
                                Text(
                                    text = error!!,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                        selectedWorkout != null && isPreviewMode -> {
                            BackHandler {
                                viewModel.clearSelectedWorkout()
                                isPreviewMode = false
                            }
                            WorkoutPreviewScreen(
                                workout = selectedWorkout!!,
                                onStartWorkout = { isPreviewMode = false },
                                onBackClick = {
                                    viewModel.clearSelectedWorkout()
                                    isPreviewMode = false
                                }
                            )
                        }
                        selectedWorkout != null -> {
                            BackHandler {
                                viewModel.clearSelectedWorkout()
                            }
                            WorkoutPlayerScreen(
                                workout = selectedWorkout!!,
                                onWorkoutComplete = {
                                    viewModel.clearSelectedWorkout()
                                },
                                onWeightUpdate = { exerciseIndex, newWeight ->
                                    viewModel.updateExerciseWeight(exerciseIndex, newWeight)
                                }
                            )
                        }
                        else -> {
                            WorkoutGroupList(
                                groups = workoutGroups,
                                onWorkoutSelected = { workout ->
                                    viewModel.loadWorkout(workout.id)
                                    isPreviewMode = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutGroupList(
    groups: List<WorkoutGroup>,
    onWorkoutSelected: (WorkoutReference) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(groups) { group ->
            WorkoutGroupCard(
                group = group,
                onWorkoutSelected = onWorkoutSelected
            )
        }
    }
}

@Composable
fun WorkoutGroupCard(
    group: WorkoutGroup,
    onWorkoutSelected: (WorkoutReference) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            group.workouts.forEach { workout ->
                WorkoutItem(
                    workout = workout,
                    onWorkoutSelected = onWorkoutSelected
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun WorkoutItem(
    workout: WorkoutReference,
    onWorkoutSelected: (WorkoutReference) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onWorkoutSelected(workout) },
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = workout.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = workout.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}