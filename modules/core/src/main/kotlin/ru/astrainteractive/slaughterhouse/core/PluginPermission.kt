package ru.astrainteractive.slaughterhouse.core

import ru.astrainteractive.astralibs.permission.Permission

/**
 * Permissions sealed itnerface
 */
sealed class PluginPermission(override val value: String) : Permission {
    data object Reload : PluginPermission("slaughterhouse.reload")
}
