package ru.astrainteractive.slaughterhouse.command.common

import ru.astrainteractive.astralibs.util.StringListExt.withEntry
import ru.astrainteractive.slaughterhouse.command.common.di.CommonCommandsDependencies
import ru.astrainteractive.slaughterhouse.game.model.GameMode

class CommonCommandsRegistry(
    dependencies: CommonCommandsDependencies
) : CommonCommandsDependencies by dependencies {

    fun register() {
        plugin.getCommand("atemp")?.setTabCompleter { sender, command, label, args ->
            when {
                args.isEmpty() -> listOf("atemp", "atempreload")
                args.size == 1 -> listOf("atemp", "atempreload").withEntry(args.last())
                else -> emptyList()
            }
        }
        plugin.getCommand("sh")?.setExecutor { sender, command, label, args ->
            if (args.getOrNull(0) == "start") {
                gameComponent.start(GameMode.DEATHMATCH)
            }
            if (args.getOrNull(0) == "stop") {
                gameComponent.stop()
            }
            true
        }
    }
}
