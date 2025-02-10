package com.seanpmcb.move.timer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.media.ToneGenerator
import android.media.AudioManager
import com.seanpmcb.move.data.Exercise
import com.seanpmcb.move.data.ExerciseType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    fun startExerciseTimer(exercise: Exercise): Flow<Int> = flow {
        // Preparation countdown if it's a work exercise
        if (exercise.type == ExerciseType.WORK) {
            for (i in 3 downTo 1) {
                playCountdownBeep()
                emit(-i) // Negative numbers indicate preparation time
                delay(1000)
            }
            playStartBeep()
        }

        // Main exercise timer
        for (i in exercise.duration downTo 1) {
            emit(i)
            delay(1000)
        }
    }

    private fun playCountdownBeep() {
        // 800Hz for 100ms
        toneGen.startTone(ToneGenerator.TONE_CDMA_PIP, 100)
    }

    private fun playStartBeep() {
        // 1200Hz for 200ms
        toneGen.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)
    }

    fun release() {
        soundPool.release()
        toneGen.release()
    }
} 