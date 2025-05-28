package com.seanpmcb.move.timer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.media.ToneGenerator
import android.media.AudioManager
import android.speech.tts.TextToSpeech
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import com.seanpmcb.move.data.MeasurementType
import com.seanpmcb.move.data.AppSettingsRepository
import com.seanpmcb.move.data.Workout
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

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
    private var tts: TextToSpeech? = null
    private var nextWorkExercise: Exercise? = null

    private var currentExercise: Exercise? = null
    private var currentFlowCollector: FlowCollector<TimerState>? = null
    private var currentTimerJob: Job? = null

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                // Try to set voice VI
                val voices = tts?.voices
                val voiceVI = voices?.find { it.name.contains("6", ignoreCase = true) }
                if (voiceVI != null) {
                    tts?.voice = voiceVI
                }
            }
        }
    }
    
    // Call this to cancel the current timer before starting a new one
    fun cancelCurrentTimer() {
        currentTimerJob?.cancel()
        currentTimerJob = null
    }

    private suspend fun speakNextExercise(workout: Workout, exerciseIndex: Int) {
        // Find next work exercise
        nextWorkExercise = workout.exercises
            .drop(exerciseIndex + 1)
            .firstOrNull { it.type == ExerciseType.WORK || it.type == ExerciseType.TRANSITION }

        val settings = settingsRepository.appSettings.first()
        if (settings.soundEffects.enabled && settings.soundEffects.nextExercise) {
            nextWorkExercise?.let { nextExercise ->
                tts?.speak("Next up: ${nextExercise.name}", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    private suspend fun speakCurrentExercise() {
        val settings = settingsRepository.appSettings.first()
        if (settings.soundEffects.enabled && settings.soundEffects.nextExercise) {
            currentExercise?.let { exercise ->
                tts?.speak(exercise.name, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    fun startExerciseTimer(
        workout: Workout,
        exerciseIndex: Int,
        isFirstExercise: Boolean = false,
        withCountdown: Boolean = false
    ): Flow<TimerState> = flow {
        // Cancel any existing timer first
        cancelCurrentTimer()
        
        val exercise = workout.exercises[exerciseIndex]
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

            // Announce current exercise
            speakCurrentExercise()

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
                } else if (i == 7 && exercise.type == ExerciseType.WORK) {
                    // Announce next work exercise when 7 seconds remain
                    speakNextExercise(workout, exerciseIndex)
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

    fun restartCurrentExercise(workout: Workout, exerciseIndex: Int): Flow<TimerState>? {
        return currentExercise?.let { startExerciseTimer(workout, exerciseIndex, withCountdown = true) }
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
        val settings = settingsRepository.appSettings.first()
        if (settings.soundEffects.enabled && settings.soundEffects.nextExercise) {
            playVictorySound()
            if (settings.soundEffects.nextExercise) {
                tts?.speak("Session Complete", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    fun release() {
        cancelCurrentTimer()
        soundPool.release()
        toneGen.release()
        tts?.stop()
        tts?.shutdown()
    }
} 