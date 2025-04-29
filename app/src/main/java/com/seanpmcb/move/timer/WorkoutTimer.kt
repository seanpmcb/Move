package com.seanpmcb.move.timer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.media.ToneGenerator
import android.media.AudioManager
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.MeasurementType
import com.seanpmcb.move.data.AppSettingsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class TimerState(
    val timeRemaining: Int,
    val isPulsing: Boolean = false
)

class WorkoutTimer(
    private val context: Context,
    private val settingsRepository: AppSettingsRepository
) {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(2)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    private val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    private var isPaused = MutableStateFlow(false)

    private var currentExercise: Exercise? = null
    private var currentFlowCollector: FlowCollector<TimerState>? = null
    private var currentTimerJob: Job? = null
    
    // Call this to cancel the current timer before starting a new one
    fun cancelCurrentTimer() {
        currentTimerJob?.cancel()
        currentTimerJob = null
    }

    fun startExerciseTimer(
        exercise: Exercise, 
        isFirstExercise: Boolean = false,
        withCountdown: Boolean = false
    ): Flow<TimerState> = flow {
        // Cancel any existing timer first
        cancelCurrentTimer()
        
        currentExercise = exercise
        currentFlowCollector = this
        // Set a reference to the current coroutine job for cancellation
        currentTimerJob = currentCoroutineContext()[Job]

        // Get current settings
        val settings = settingsRepository.appSettings.first()

        // For TIME-based exercises, use the timer
        if (exercise.measurementType == null) {
            // Initial countdown for first exercise or restart
            if (isFirstExercise || withCountdown) {
                for (i in 5 downTo 1) {
                    // Emit without pulse first
                    emit(TimerState(
                        timeRemaining = -i, // Negative numbers indicate preparation countdown
                        isPulsing = false
                    ))
                    if (i <= 3) {
                        // Emit with pulse and play beep simultaneously
                        emit(TimerState(
                            timeRemaining = -i,
                            isPulsing = settings.visualEffects.enabled && settings.visualEffects.countdownPulse
                        ))
                        playCountdownBeep()
                    }
                    delay(1000)
                    while (isPaused.value) {
                        delay(100)
                    }
                }
            }

            // Main exercise timer
            for (i in exercise.duration!! downTo 1) {
                // Emit without pulse first
                emit(TimerState(
                    timeRemaining = i,
                    isPulsing = false
                ))
                if (i <= 3) {
                    // Emit with pulse and play beep simultaneously
                    emit(TimerState(
                        timeRemaining = i,
                        isPulsing = settings.visualEffects.enabled && settings.visualEffects.countdownPulse
                    ))
                    playCountdownBeep()
                }
                delay(1000)
                while (isPaused.value) {
                    delay(100)
                }
            }
            // Final state with pulse
            emit(TimerState(
                timeRemaining = 0,
                isPulsing = settings.visualEffects.enabled && settings.visualEffects.countdownPulse
            ))
        } else {
            // For rep-based or custom exercises, just emit 1 as a placeholder
            // This prevents the timer from immediately finishing and keeps the exercise displayed
            // The user will tap the "Complete Exercise" button to progress
            emit(TimerState(timeRemaining = 1))
        }
    }.catch { e ->
        // Handle cancellation or errors gracefully
        if (e is CancellationException) {
            // Timer was cancelled, clean up if needed
        } else {
            // Log other errors
            println("Error in timer: ${e.message}")
        }
    }

    fun restartCurrentExercise(): Flow<TimerState>? {
        return currentExercise?.let { startExerciseTimer(it, withCountdown = true) }
    }

    fun togglePause() {
        isPaused.value = !isPaused.value
    }

    fun isPaused(): Flow<Boolean> = isPaused.asStateFlow()

    private fun playCountdownBeep() {
        // Check if sound effects are enabled and step countdown is enabled
        val settings = runBlocking { settingsRepository.appSettings.first() }
        if (settings.soundEffects.enabled && settings.soundEffects.stepCountdown) {
            // 800Hz for 100ms
            toneGen.startTone(ToneGenerator.TONE_CDMA_PIP, 100)
        }
    }

    private suspend fun playVictorySound() {
        // Check if sound effects are enabled
        val settings = settingsRepository.appSettings.first()
        if (settings.soundEffects.enabled) {
            // Single lower pitch beep instead of high-pitched double beep
            toneGen.startTone(ToneGenerator.TONE_CDMA_PIP, 300)
        }
    }

    suspend fun playWorkoutCompleteSound() {
        playVictorySound()
    }

    fun release() {
        cancelCurrentTimer()
        soundPool.release()
        toneGen.release()
    }
} 