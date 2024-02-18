package ru.astrainteractive.slaughterhouse.game.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object CoroutineCountdownExt {
    suspend fun coroutineCountdown(
        duration: Duration,
        onTimePassed: suspend CoroutineScope.(timeLeft: Duration) -> Unit
    ) = supervisorScope {
        val started = System.currentTimeMillis()
        onTimePassed.invoke(this, duration)
        do {
            val timeLeft = (started + duration.inWholeMilliseconds) - System.currentTimeMillis()
            onTimePassed.invoke(this, timeLeft.milliseconds)
            delay(1000L)
        } while (timeLeft > 0L)
    }
}
