package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seanpmcb.move.data.Workout

@Composable
fun WorkoutCompleteScreen(
    workout: Workout,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🎉 Congratulations! 🎉",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "You've completed",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Text(
            text = workout.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Total Duration: ${workout.totalDuration / 60}m ${workout.totalDuration % 60}s",
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        FilledTonalButton(
            onClick = onDismiss,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Return to Workouts")
        }
    }
} 