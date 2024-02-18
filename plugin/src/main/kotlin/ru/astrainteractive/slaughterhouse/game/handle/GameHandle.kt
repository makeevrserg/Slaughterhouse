package ru.astrainteractive.slaughterhouse.game.handle

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Location
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.lifecycle.Lifecycle
import ru.astrainteractive.slaughterhouse.game.di.GameHandleDependencies
import ru.astrainteractive.slaughterhouse.game.presentation.GameComponent
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

class GameHandle(
    private val gameComponent: GameComponent,
    dependencies: GameHandleDependencies
) : Lifecycle, GameHandleDependencies by dependencies {
    private val scope = AsyncComponent.Default()

    private fun watchDeathMatch() {
        gameComponent.model.filterIsInstance<GameComponent.Model.Started.DeathMatch>()
            .distinctUntilChanged()
            .onEach { model ->
                Bukkit.getOnlinePlayers().forEach { player ->
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
            .launchIn(scope)
    }

    private fun watchPending() {
        gameComponent.model
            .filterIsInstance<GameComponent.Model.Pending>()
            .onEach {
                val location = Location(
                    Bukkit.getWorld(pluginConfiguration.spawn.world),
                    pluginConfiguration.spawn.x,
                    pluginConfiguration.spawn.y,
                    pluginConfiguration.spawn.z,
                )
                Bukkit.getOnlinePlayers().forEach { player ->
                    player.inventory.clear()
                    player.teleportAsync(location)
                }
            }
            .launchIn(scope)
    }

    private fun watchCountdown() {
        gameComponent.model.filterIsInstance<GameComponent.Model.Countdown>()
            .distinctUntilChanged()
            .onEach { countdown ->
                Bukkit.getOnlinePlayers().forEach { player ->
                    val title = Title.title(
                        Component.text("Get Ready!"),
                        Component.text("Start in ${countdown.duration.inWholeSeconds} sec"),
                        Title.Times.times(
                            1.seconds.toJavaDuration(),
                            1.seconds.toJavaDuration(),
                            1.seconds.toJavaDuration()
                        )
                    )
                    player.showTitle(title)
                }
            }
            .launchIn(scope)
    }

    override fun onEnable() {
        watchCountdown()
        watchPending()
        watchDeathMatch()
    }

    override fun onDisable() {
        scope.close()
    }
}
