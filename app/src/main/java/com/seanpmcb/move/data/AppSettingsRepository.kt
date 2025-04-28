package com.seanpmcb.move.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.appSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class AppSettingsRepository(private val context: Context) {
    private val soundEnabledKey = booleanPreferencesKey("sound_enabled")
    private val soundStepCountdownKey = booleanPreferencesKey("sound_step_countdown")
    private val soundNextExerciseKey = booleanPreferencesKey("sound_next_exercise")
    private val soundExerciseDescriptionKey = booleanPreferencesKey("sound_exercise_description")
    private val visualEffectsKey = booleanPreferencesKey("visual_effects")

    val appSettings: Flow<AppSettings> = context.appSettingsDataStore.data
        .map { preferences ->
            AppSettings(
                soundEffects = SoundEffects(
                    enabled = preferences[soundEnabledKey] ?: true,
                    stepCountdown = preferences[soundStepCountdownKey] ?: true,
                    nextExercise = preferences[soundNextExerciseKey] ?: true,
                    exerciseDescription = preferences[soundExerciseDescriptionKey] ?: true
                ),
                visualEffects = preferences[visualEffectsKey] ?: true
            )
        }

    suspend fun updateSoundEnabled(enabled: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[soundEnabledKey] = enabled
        }
    }

    suspend fun updateSoundStepCountdown(enabled: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[soundStepCountdownKey] = enabled
        }
    }

    suspend fun updateSoundNextExercise(enabled: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[soundNextExerciseKey] = enabled
        }
    }

    suspend fun updateSoundExerciseDescription(enabled: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[soundExerciseDescriptionKey] = enabled
        }
    }

    suspend fun updateVisualEffects(enabled: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[visualEffectsKey] = enabled
        }
    }
} 