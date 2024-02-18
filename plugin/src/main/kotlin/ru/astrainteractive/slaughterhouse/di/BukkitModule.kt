package ru.astrainteractive.slaughterhouse.di

import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.serialization.KyoriComponentSerializer
import ru.astrainteractive.klibs.kdi.Dependency
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.slaughterhouse.Slaughterhouse

interface BukkitModule {
    val plugin: Lateinit<Slaughterhouse>
    val bukkitDispatchers: Dependency<BukkitDispatchers>
    val kyoriComponentSerializer: Single<KyoriComponentSerializer>
}
