package ru.astrainteractive.slaughterhouse.game.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.slaughterhouse.core.PluginConfiguration
import ru.astrainteractive.slaughterhouse.game.model.GameMode
import kotlin.time.Duration

interface GameComponent : CoroutineScope {
    val model: StateFlow<Model>

    fun start(gameMode: GameMode)

    fun stop()

    sealed interface Model {
        data object Pending : Model
        data class Countdown(val duration: Duration) : Model
        sealed interface Started : Model {
            data class DeathMatch(val arena: PluginConfiguration.Arena) : Started
        }
    }
}
