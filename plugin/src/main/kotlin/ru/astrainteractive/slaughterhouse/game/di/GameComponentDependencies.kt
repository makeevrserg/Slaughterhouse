package ru.astrainteractive.slaughterhouse.game.di

import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.slaughterhouse.core.PluginConfiguration
import ru.astrainteractive.slaughterhouse.core.di.CoreModule

interface GameComponentDependencies {
    val pluginConfig: PluginConfiguration

    class Default(coreModule: CoreModule) : GameComponentDependencies {
        override val pluginConfig: PluginConfiguration by coreModule.configurationModule
    }
}
