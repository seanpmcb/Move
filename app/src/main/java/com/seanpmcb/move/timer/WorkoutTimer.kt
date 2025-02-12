package com.seanpmcb.move.timer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.media.ToneGenerator
import android.media.AudioManager
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class WorkoutTimer(private val context: Context) {
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
    private var currentFlowCollector: FlowCollector<Int>? = null

    fun startExerciseTimer(
        exercise: Exercise, 
        isFirstExercise: Boolean = false,
        withCountdown: Boolean = false
    ): Flow<Int> = flow {
        currentExercise = exercise
        currentFlowCollector = this

        // Initial countdown for first exercise or restart
        if (isFirstExercise || withCountdown) {
            for (i in 5 downTo 1) {
                emit(-i) // Negative numbers indicate preparation countdown
                if (i <= 3) {
                    playCountdownBeep()
                }
                delay(1000)
                while (isPaused.value) {
                    delay(100)
                }
            }
        }

        // Main exercise timer
        for (i in exercise.duration downTo 1) {
            emit(i)
            if (i <= 3) {
                playCountdownBeep()
            }
            delay(1000)
            while (isPaused.value) {
                delay(100)
            }
        }
        emit(0) // Emit final zero
    }

    fun restartCurrentExercise(): Flow<Int>? {
        return currentExercise?.let { startExerciseTimer(it, withCountdown = true) }
    }

    fun togglePause() {
        isPaused.value = !isPaused.value
    }

    fun isPaused(): Flow<Boolean> = isPaused.asStateFlow()

    private fun playCountdownBeep() {
        // 800Hz for 100ms
        toneGen.startTone(ToneGenerator.TONE_CDMA_PIP, 100)
    }

    private suspend fun playVictorySound() {
        // Single lower pitch beep instead of high-pitched double beep
        toneGen.startTone(ToneGenerator.TONE_CDMA_PIP, 300)
    }

    suspend fun playWorkoutCompleteSound() {
        playVictorySound()
    }

    fun release() {
        soundPool.release()
        toneGen.release()
    }
} 