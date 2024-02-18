package ru.astrainteractive.slaughterhouse.core

import kotlinx.serialization.Serializable
import ru.astrainteractive.slaughterhouse.core.serialization.DurationSerializer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Example config file with kotlinx.serialization
 */
@Serializable
data class PluginConfiguration(
    val spawn: Spawn = Spawn(),
    @Serializable(with = DurationSerializer::class)
    val countdown: Duration = 5.seconds,
    val arenas: List<Arena> = emptyList()
) {

    @Serializable
    data class Arena(
        val id: String,
        val spawns: List<Spawn> = emptyList(),
    )

    @Serializable
    data class Spawn(
        val x: Double = 0.0,
        val y: Double = 0.0,
        val z: Double = 0.0,
        val world: String = "world"
    )
}
