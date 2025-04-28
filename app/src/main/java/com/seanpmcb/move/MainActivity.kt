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
import androidx.compose.ui.Alignment
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
import com.seanpmcb.move.ui.SettingsScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isPreviewMode by remember { mutableStateOf(false) }
            var showSettings by remember { mutableStateOf(false) }
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
                        showSettings -> {
                            BackHandler {
                                showSettings = false
                            }
                            SettingsScreen(
                                onBackClick = { showSettings = false }
                            )
                        }
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
                                },
                                workoutDurations = viewModel.workoutDurations.collectAsState().value,
                                onSettingsClick = { showSettings = true }
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
    onWorkoutSelected: (WorkoutReference) -> Unit,
    workoutDurations: Map<String, Int?> = emptyMap(),
    onSettingsClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(groups) { group ->
            WorkoutGroupCard(
                group = group,
                onWorkoutSelected = onWorkoutSelected,
                workoutDurations = workoutDurations,
                showSettings = groups.indexOf(group) == 0,
                onSettingsClick = onSettingsClick
            )
        }
    }
}

@Composable
fun WorkoutGroupCard(
    group: WorkoutGroup,
    onWorkoutSelected: (WorkoutReference) -> Unit,
    workoutDurations: Map<String, Int?> = emptyMap(),
    showSettings: Boolean = false,
    onSettingsClick: () -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                if (showSettings) {
                    IconButton(
                        onClick = onSettingsClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            group.workouts.forEach { workout ->
                WorkoutItem(
                    workout = workout,
                    onWorkoutSelected = onWorkoutSelected,
                    duration = workoutDurations[workout.id]
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun WorkoutItem(
    workout: WorkoutReference,
    onWorkoutSelected: (WorkoutReference) -> Unit,
    duration: Int? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onWorkoutSelected(workout) },
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
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
            
            // Display duration if available
            duration?.let { totalSeconds ->
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                Text(
                    text = "${minutes}m ${seconds}s",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}