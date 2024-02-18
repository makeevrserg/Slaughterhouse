package ru.astrainteractive.slaughterhouse.core.di.factory

import kotlinx.serialization.encodeToString
import ru.astrainteractive.astralibs.filemanager.impl.JVMFileManager
import ru.astrainteractive.astralibs.serialization.SerializerExt.parseOrDefault
import ru.astrainteractive.astralibs.serialization.YamlSerializer
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.slaughterhouse.core.PluginTranslation
import java.io.File

internal class TranslationFactory(
    private val dataFolder: File,
    private val yamlSerializer: YamlSerializer
) : Factory<PluginTranslation> {

    override fun create(): PluginTranslation {
        val configFile = JVMFileManager("translations.yml", dataFolder)
        val translation = yamlSerializer.parseOrDefault(
            configFile.configFile,
            ::PluginTranslation
        )
        if (!configFile.configFile.exists()) {
            configFile.configFile.createNewFile()
        }
        configFile.configFile.writeText(yamlSerializer.yaml.encodeToString(translation))
        return translation
    }
}
