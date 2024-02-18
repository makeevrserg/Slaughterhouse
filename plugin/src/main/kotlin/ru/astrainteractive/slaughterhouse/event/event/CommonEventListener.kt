package ru.astrainteractive.slaughterhouse.event.event

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.spigotmc.event.player.PlayerSpawnLocationEvent
import ru.astrainteractive.astralibs.event.EventListener
import ru.astrainteractive.slaughterhouse.event.di.CommonEventDependencies
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent

/**
 * This is a more convenient way with base class
 */
internal class CommonEventListener(
    dependencies: CommonEventDependencies
) : EventListener, CommonEventDependencies by dependencies {

    @org.bukkit.event.EventHandler
    fun playerDeathEvent(e: PlayerDeathEvent) {
        e.keepInventory = true
        e.keepLevel = true
        e.drops.clear()
    }

    @org.bukkit.event.EventHandler
    fun playerJoinEvent(e: PlayerJoinEvent) {
        val player = e.player
        when (val model = gameComponent.model.value) {
            is GameComponent.Model.Countdown, GameComponent.Model.Pending -> {
                val location = Location(
                    Bukkit.getWorld(pluginConfiguration.spawn.world),
                    pluginConfiguration.spawn.x,
                    pluginConfiguration.spawn.y,
                    pluginConfiguration.spawn.z,
                )
                player.inventory.clear()
                player.teleportAsync(location)
            }

            is GameComponent.Model.Started.DeathMatch -> {
                val spawn = model.arena.spawns.random()
                val location = Location(
                    Bukkit.getWorld(spawn.world),
                    spawn.x,
                    spawn.y,
                    spawn.z,
                )
                player.inventory.clear()
                player.teleportAsync(location)
            }
        }
    }

    @org.bukkit.event.EventHandler
    fun playerSpawnEvent(e: PlayerSpawnLocationEvent) {
        val player = e.player
        when (val model = gameComponent.model.value) {
            is GameComponent.Model.Countdown, GameComponent.Model.Pending -> {
                val location = Location(
                    Bukkit.getWorld(pluginConfiguration.spawn.world),
                    pluginConfiguration.spawn.x,
                    pluginConfiguration.spawn.y,
                    pluginConfiguration.spawn.z,
                )
                player.inventory.clear()
                player.teleportAsync(location)
            }

            is GameComponent.Model.Started.DeathMatch -> {
                val spawn = model.arena.spawns.random()
                val location = Location(
                    Bukkit.getWorld(spawn.world),
                    spawn.x,
                    spawn.y,
                    spawn.z,
                )
                player.inventory.clear()
                player.teleportAsync(location)
            }
        }
    }
}
