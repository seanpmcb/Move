package com.seanpmcb.move.data

data class AppSettings(
    val soundEffects: SoundEffects = SoundEffects(),
    val visualEffects: VisualEffects = VisualEffects()
)

data class SoundEffects(
    val enabled: Boolean = true,
    val stepCountdown: Boolean = true,
    val nextExercise: Boolean = true,
    val exerciseDescription: Boolean = true
)

data class VisualEffects(
    val enabled: Boolean = true,
    val countdownPulse: Boolean = true
) 