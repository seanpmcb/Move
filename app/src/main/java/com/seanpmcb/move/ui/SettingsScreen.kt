package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.clickable
import com.seanpmcb.move.data.AppSettingsRepository
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val settingsRepository = remember { AppSettingsRepository(context) }
    val settings by settingsRepository.appSettings.collectAsState(initial = null)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Settings options
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
                // Master sound effects toggle
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
                                text = "Sound Effects",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Enable or disable all sound effects",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Switch(
                            checked = settings?.soundEffects?.enabled ?: true,
                            onCheckedChange = { enabled ->
                                scope.launch {
                                    settingsRepository.updateSoundEnabled(enabled)
                                }
                            }
                        )
                    }
                
                // Sub-settings for Sound Effects
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { 
                                if (settings?.soundEffects?.enabled == true) {
                                    scope.launch {
                                        settingsRepository.updateSoundStepCountdown(!(settings?.soundEffects?.stepCountdown ?: true))
                                    }
                                }
                            },
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
                                    text = "Step countdown",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Enable or disable step countdown",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Switch(
                                checked = settings?.soundEffects?.stepCountdown ?: true,
                                onCheckedChange = { enabled ->
                                    if (settings?.soundEffects?.enabled == true) {
                                        scope.launch {
                                            settingsRepository.updateSoundStepCountdown(enabled)
                                        }
                                    }
                                },
                                enabled = settings?.soundEffects?.enabled ?: true
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { 
                                if (settings?.soundEffects?.enabled == true) {
                                    scope.launch {
                                        settingsRepository.updateSoundNextExercise(!(settings?.soundEffects?.nextExercise ?: true))
                                    }
                                }
                            },
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
                                    text = "Next exercise",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Enable or disable next exercise announcement",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Switch(
                                checked = settings?.soundEffects?.nextExercise ?: true,
                                onCheckedChange = { enabled ->
                                    if (settings?.soundEffects?.enabled == true) {
                                        scope.launch {
                                            settingsRepository.updateSoundNextExercise(enabled)
                                        }
                                    }
                                },
                                enabled = settings?.soundEffects?.enabled ?: true
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { 
                                if (settings?.soundEffects?.enabled == true) {
                                    scope.launch {
                                        settingsRepository.updateSoundExerciseDescription(!(settings?.soundEffects?.exerciseDescription ?: true))
                                    }
                                }
                            },
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
                                    text = "Exercise description",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Enable or disable exercise description",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Switch(
                                checked = settings?.soundEffects?.exerciseDescription ?: true,
                                onCheckedChange = { enabled ->
                                    if (settings?.soundEffects?.enabled == true) {
                                        scope.launch {
                                            settingsRepository.updateSoundExerciseDescription(enabled)
                                        }
                                    }
                                },
                                enabled = settings?.soundEffects?.enabled ?: true
                            )
                        }
                    }
                }
            }
        }

        // Visual Effects card
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
                // Master visual effects toggle
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
                            text = "Visual Effects",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Enable or disable all visual effects",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Switch(
                        checked = settings?.visualEffects?.enabled ?: true,
                        onCheckedChange = { enabled ->
                            scope.launch {
                                settingsRepository.updateVisualEffectsEnabled(enabled)
                            }
                        }
                    )
                }
                
                // Sub-settings for Visual Effects
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { 
                                if (settings?.visualEffects?.enabled == true) {
                                    scope.launch {
                                        settingsRepository.updateVisualEffectsCountdownPulse(!(settings?.visualEffects?.countdownPulse ?: true))
                                    }
                                }
                            },
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
                                    text = "Countdown pulse",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Enable or disable countdown pulse effect",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Switch(
                                checked = settings?.visualEffects?.countdownPulse ?: true,
                                onCheckedChange = { enabled ->
                                    if (settings?.visualEffects?.enabled == true) {
                                        scope.launch {
                                            settingsRepository.updateVisualEffectsCountdownPulse(enabled)
                                        }
                                    }
                                },
                                enabled = settings?.visualEffects?.enabled ?: true
                            )
                        }
                    }
                }
            }
        }
    }
} 