package ru.astrainteractive.slaughterhouse.command.common.di

import org.bukkit.plugin.java.JavaPlugin
import ru.astrainteractive.astralibs.serialization.KyoriComponentSerializer
import ru.astrainteractive.slaughterhouse.core.PluginTranslation
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent

interface CommonCommandsDependencies {
    val plugin: JavaPlugin
    val translation: PluginTranslation
    val kyoriComponentSerializer: KyoriComponentSerializer
    val gameComponent: GameComponent
}
