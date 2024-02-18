package ru.astrainteractive.slaughterhouse.game.di

import kotlinx.coroutines.cancel
import ru.astrainteractive.astralibs.lifecycle.Lifecycle
import ru.astrainteractive.slaughterhouse.core.di.CoreModule
import ru.astrainteractive.slaughterhouse.game.handle.GameHandle
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent
import ru.astrainteractive.slaughterhouse.game.presentation.GameFeature

interface GameModule {
    val lifecycle: Lifecycle
    val gameComponent: GameComponent

    class Default(coreModule: CoreModule) : GameModule {

        override val gameComponent: GameComponent by lazy {
            val dependencies = GameComponentDependencies.Default(coreModule = coreModule)
            GameFeature(
                dependencies = dependencies
            )
        }

        private val gameHandle by lazy {
            val dependencies = GameHandleDependencies.Default(coreModule = coreModule)
            GameHandle(
                dependencies = dependencies,
                gameComponent = gameComponent
            )
        }

        override val lifecycle: Lifecycle by lazy {
            Lifecycle.Lambda(
                onEnable = {
                    gameHandle.onEnable()
                },
                onReload = {
                    gameHandle.onReload()
                },
                onDisable = {
                    gameComponent.cancel()
                    gameHandle.onDisable()
                }
            )
        }
    }
}
