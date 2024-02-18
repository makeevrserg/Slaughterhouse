package ru.astrainteractive.slaughterhouse.command.reload.di

import org.bukkit.plugin.java.JavaPlugin
import ru.astrainteractive.astralibs.serialization.KyoriComponentSerializer
import ru.astrainteractive.slaughterhouse.core.PluginTranslation

interface ReloadCommandDependencies {
    val plugin: JavaPlugin
    val translation: PluginTranslation
    val kyoriComponentSerializer: KyoriComponentSerializer
}
