package ru.astrainteractive.slaughterhouse.command.di

import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.slaughterhouse.command.common.di.CommonCommandsDependencies
import ru.astrainteractive.slaughterhouse.command.reload.di.ReloadCommandDependencies
import ru.astrainteractive.slaughterhouse.di.RootModule
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent

interface CommandManagerDependencies :
    ReloadCommandDependencies,
    CommonCommandsDependencies {
    class Default(rootModule: RootModule) : CommandManagerDependencies {
        override val plugin by rootModule.bukkitModule.plugin
        override val translation by rootModule.coreModule.translation
        override val kyoriComponentSerializer by rootModule.bukkitModule.kyoriComponentSerializer
        override val gameComponent: GameComponent = rootModule.gameModule.gameComponent
    }
}
