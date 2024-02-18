package ru.astrainteractive.slaughterhouse.game.di

import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.slaughterhouse.core.PluginConfiguration
import ru.astrainteractive.slaughterhouse.core.di.CoreModule

interface GameHandleDependencies {
    val pluginConfiguration: PluginConfiguration

    class Default(coreModule: CoreModule) : GameHandleDependencies {
        override val pluginConfiguration: PluginConfiguration by coreModule.configurationModule
    }
}
