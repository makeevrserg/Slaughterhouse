package ru.astrainteractive.slaughterhouse.game.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.slaughterhouse.game.di.GameComponentDependencies
import ru.astrainteractive.slaughterhouse.game.model.GameMode
import ru.astrainteractive.slaughterhouse.game.util.CoroutineCountdownExt.coroutineCountdown
import kotlin.time.Duration.Companion.seconds

class GameFeature(
    private val dependencies: GameComponentDependencies
) : GameComponent,
    CoroutineScope by AsyncComponent.Default(),
    GameComponentDependencies by dependencies {
    override val model = MutableStateFlow<GameComponent.Model>(GameComponent.Model.Pending)

    override fun start(gameMode: GameMode) {
        launch {
            coroutineCountdown(10.seconds) { timeLeft ->
                model.value = GameComponent.Model.Countdown(timeLeft)
            }
            val arena = pluginConfig.arenas.randomOrNull()
            if (arena == null) {
                stop()
                return@launch
            }
            model.value = GameComponent.Model.Started.DeathMatch(
                arena = arena
            )
        }
    }

    override fun stop() {
        model.value = GameComponent.Model.Pending
    }
}
