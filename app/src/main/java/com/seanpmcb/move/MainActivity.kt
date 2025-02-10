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
import com.seanpmcb.move.data.WorkoutRepository
import com.seanpmcb.move.ui.theme.MoveTheme
import com.seanpmcb.move.ui.WorkoutPlayerScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.seanpmcb.move.ui.WorkoutPreviewScreen

class MainActivity : ComponentActivity() {
    private val workoutRepository = WorkoutRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedWorkout by remember { mutableStateOf<Workout?>(null) }
            var isPreviewMode by remember { mutableStateOf(false) }
            
            MoveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        selectedWorkout != null && isPreviewMode -> {
                            BackHandler {
                                selectedWorkout = null
                                isPreviewMode = false
                            }
                            WorkoutPreviewScreen(
                                workout = selectedWorkout!!,
                                onStartWorkout = { isPreviewMode = false },
                                onBack = {
                                    selectedWorkout = null
                                    isPreviewMode = false
                                }
                            )
                        }
                        selectedWorkout != null -> {
                            BackHandler {
                                selectedWorkout = null
                            }
                            WorkoutPlayerScreen(
                                workout = selectedWorkout!!,
                                onWorkoutComplete = {
                                    selectedWorkout = null
                                }
                            )
                        }
                        else -> {
                            WorkoutGroupList(
                                groups = workoutRepository.getAllWorkoutGroups(),
                                onWorkoutSelected = { workout ->
                                    selectedWorkout = workout
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
    onWorkoutSelected: (Workout) -> Unit
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
    onWorkoutSelected: (Workout) -> Unit
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
    workout: Workout,
    onWorkoutSelected: (Workout) -> Unit
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
            Text(
                text = "Duration: ${workout.totalDuration / 60}m ${workout.totalDuration % 60}s",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}