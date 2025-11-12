package mc.utils

import kotlinx.serialization.json.*
import java.io.File

object TranslationManager {
    private var translations: JsonObject = JsonObject(emptyMap())

    fun load(language: String) {
        val file = File("../../i18n/$language.json")
        if (!file.exists()) {
            println("⚠️ Translation file not found for $language, falling back to English")
            return load("en")
        }
        val content = file.readText()
        translations = Json.parseToJsonElement(content).jsonObject
    }

    fun get(key: String): String {
        val parts = key.split(".")
        var current: JsonElement = translations
        for (part in parts) {
            current = current.jsonObject[part] ?: return "??$key??"
        }
        return current.jsonPrimitive.content
    }
}
