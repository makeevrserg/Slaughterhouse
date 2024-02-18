package ru.astrainteractive.slaughterhouse.event.di

import ru.astrainteractive.astralibs.event.EventListener
import ru.astrainteractive.astralibs.lifecycle.Lifecycle
import ru.astrainteractive.astralibs.menu.event.DefaultInventoryClickEvent
import ru.astrainteractive.slaughterhouse.di.RootModule
import ru.astrainteractive.slaughterhouse.event.event.CommonEventListener

interface EventModule {
    val lifecycle: Lifecycle

    class Default(rootModule: RootModule) : EventModule {

        private val events = buildList {

            CommonEventListener(
                dependencies = CommonEventDependencies.Default(
                    rootModule = rootModule,
                )
            ).also(::add)
            DefaultInventoryClickEvent().also(::add)
        }

        override val lifecycle: Lifecycle by lazy {
            val plugin = rootModule.bukkitModule.plugin.value
            Lifecycle.Lambda(
                onEnable = {
                    events.forEach { it.onEnable(plugin) }
                },
                onDisable = {
                    events.forEach(EventListener::onDisable)
                }
            )
        }
    }
}
