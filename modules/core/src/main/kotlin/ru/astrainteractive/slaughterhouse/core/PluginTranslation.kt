@file:Suppress("LongParameterList")

package ru.astrainteractive.slaughterhouse.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.astrainteractive.astralibs.string.StringDesc

/**
 * All translation stored here
 * Each translation have default value so it's not necesarry to fetch it from resources
 */
@Serializable
class PluginTranslation(
    val general: General = General()
) {
    @Serializable
    class General(
        @SerialName("prefix")
        val prefix: StringDesc.Raw = StringDesc.Raw("&#18dbd1[Slaughterhouse]"),
        @SerialName("reload")
        val reload: StringDesc.Raw = StringDesc.Raw("&#dbbb18Перезагрузка плагина"),
        @SerialName("reload_complete")
        val reloadComplete: StringDesc.Raw = StringDesc.Raw("&#42f596Перезагрузка успешно завершена"),
        @SerialName("no_permission")
        val noPermission: StringDesc.Raw = StringDesc.Raw("&#db2c18У вас нет прав!"),
        @SerialName("not_player")
        val notPlayer: StringDesc.Raw = StringDesc.Raw("&#db2c18Вы не игрок"),
    )
}
