package ru.astrainteractive.slaughterhouse.di.impl

import ru.astrainteractive.slaughterhouse.command.di.CommandModule
import ru.astrainteractive.slaughterhouse.core.di.CoreModule
import ru.astrainteractive.slaughterhouse.di.BukkitModule
import ru.astrainteractive.slaughterhouse.di.RootModule
import ru.astrainteractive.slaughterhouse.event.di.EventModule
import ru.astrainteractive.slaughterhouse.game.di.GameModule

internal class RootModuleImpl : RootModule {

    override val bukkitModule: BukkitModule by lazy {
        BukkitModuleImpl()
    }

    override val coreModule: CoreModule by lazy {
        CoreModule.Default(bukkitModule.plugin.value.dataFolder)
    }

    override val eventModule: EventModule by lazy {
        EventModule.Default(this)
    }

    override val commandModule: CommandModule by lazy {
        CommandModule.Default(this)
    }

    override val gameModule: GameModule by lazy {
        GameModule.Default(
            coreModule = coreModule
        )
    }
}
