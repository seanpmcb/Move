package com.seanpmcb.move.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.clickable

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
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
                // Sound settings
                Text(
                    text = "Sound Effects",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                // Sub-settings for Sound Effects
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* TODO: Handle click */ },
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
                                checked = true, // TODO: Connect to actual settings
                                onCheckedChange = { /* TODO: Update settings */ }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* TODO: Handle click */ },
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
                                checked = true, // TODO: Connect to actual settings
                                onCheckedChange = { /* TODO: Update settings */ }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* TODO: Handle click */ },
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
                                checked = true, // TODO: Connect to actual settings
                                onCheckedChange = { /* TODO: Update settings */ }
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
                Text(
                    text = "Visual Effects",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: Handle click */ },
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
                                text = "Enable visual effects",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Enable or disable visual effects",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Switch(
                            checked = true, // TODO: Connect to actual settings
                            onCheckedChange = { /* TODO: Update settings */ }
                        )
                    }
                }
            }
        }
    }
} 