package mc.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.json.*
import java.io.InputStreamReader
import java.util.*

object TranslationManager {
    private var translations: MutableState<JsonObject> = mutableStateOf(JsonObject(emptyMap()))

    fun load(language: String) {
        val resourcePath = "/i18n/$language.json"
        val stream = TranslationManager::class.java.getResourceAsStream(resourcePath)
        if (stream == null) {
            println("⚠️ Translation file not found for '$language', falling back to English")
            if (language != "en") load("en")
            return
        }

        val content = InputStreamReader(stream, Charsets.UTF_8).readText()
        translations.value = Json.parseToJsonElement(content).jsonObject
        println("✅ Loaded translations for language: $language")
    }

    fun get(key: String): String {
        val parts = key.split(".")
        var current: JsonElement = translations.value
        for (part in parts) {
            val next = current.jsonObject[part] ?: return "??$key??"
            current = next
        }
        return current.jsonPrimitive.content
    }
}
