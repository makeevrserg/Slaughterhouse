package ru.astrainteractive.slaughterhouse.command.di

import ru.astrainteractive.astralibs.lifecycle.Lifecycle
import ru.astrainteractive.slaughterhouse.command.common.CommonCommandsRegistry
import ru.astrainteractive.slaughterhouse.command.reload.ReloadCommandRegistry
import ru.astrainteractive.slaughterhouse.di.RootModule

interface CommandModule {
    val lifecycle: Lifecycle

    class Default(rootModule: RootModule) : CommandModule {
        override val lifecycle: Lifecycle by lazy {
            Lifecycle.Lambda(
                onEnable = {
                    val dependencies = CommandManagerDependencies.Default(rootModule)
                    CommonCommandsRegistry(dependencies).register()
                    ReloadCommandRegistry(dependencies).register()
                }
            )
        }
    }
}
