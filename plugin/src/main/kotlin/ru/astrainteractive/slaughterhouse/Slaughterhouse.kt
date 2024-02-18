package ru.astrainteractive.slaughterhouse

import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import ru.astrainteractive.astralibs.lifecycle.Lifecycle
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.slaughterhouse.di.impl.RootModuleImpl

/**
 * Initial class for your plugin
 */

class Slaughterhouse : JavaPlugin() {
    private val rootModule = RootModuleImpl()
    private val lifecycles: List<Lifecycle>
        get() = listOf(
            rootModule.coreModule.lifecycle,
            rootModule.eventModule.lifecycle,
            rootModule.gameModule.lifecycle,
            rootModule.commandModule.lifecycle
        )

    init {
        rootModule.bukkitModule.plugin.initialize(this)
    }

    override fun onEnable() {
        lifecycles.forEach(Lifecycle::onEnable)
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
        lifecycles.forEach(Lifecycle::onDisable)
    }

    fun reloadPlugin() {
        lifecycles.forEach(Lifecycle::onReload)
    }
}
