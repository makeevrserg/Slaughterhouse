package ru.astrainteractive.slaughterhouse.event.di

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.serialization.KyoriComponentSerializer
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.slaughterhouse.core.PluginConfiguration
import ru.astrainteractive.slaughterhouse.core.PluginTranslation
import ru.astrainteractive.slaughterhouse.di.RootModule
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent

internal interface CommonEventDependencies {
    val plugin: Plugin
    val translation: PluginTranslation
    val kyoriComponentSerializer: KyoriComponentSerializer
    val gameComponent: GameComponent
    val pluginConfiguration: PluginConfiguration

    class Default(
        rootModule: RootModule,
    ) : CommonEventDependencies {
        override val plugin by rootModule.bukkitModule.plugin
        override val translation by rootModule.coreModule.translation
        override val kyoriComponentSerializer by rootModule.bukkitModule.kyoriComponentSerializer
        override val gameComponent: GameComponent = rootModule.gameModule.gameComponent
        override val pluginConfiguration: PluginConfiguration by rootModule.coreModule.configurationModule
    }
}
