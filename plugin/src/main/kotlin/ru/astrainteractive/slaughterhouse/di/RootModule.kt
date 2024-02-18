package ru.astrainteractive.slaughterhouse.di

import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.slaughterhouse.command.di.CommandModule
import ru.astrainteractive.slaughterhouse.core.di.CoreModule
import ru.astrainteractive.slaughterhouse.event.di.EventModule
import ru.astrainteractive.slaughterhouse.game.di.GameModule

interface RootModule : Module {
    val bukkitModule: BukkitModule

    val coreModule: CoreModule

    val eventModule: EventModule

    val commandModule: CommandModule

    val gameModule: GameModule
}
