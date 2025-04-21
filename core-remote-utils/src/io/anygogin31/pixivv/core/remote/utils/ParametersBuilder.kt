package io.anygogin31.pixivv.core.remote.utils

import io.ktor.http.ParametersBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.encodeToJsonElement

public val defaultJson: Json = Json {
    encodeDefaults = true
}

public inline fun <reified T : Any> ParametersBuilder.append(value: T) {
    val json: Json = defaultJson
    val jsonElement: JsonElement = json.encodeToJsonElement(value)
    with(jsonElement) {
        when (this) {
            is JsonObject -> {
                entries.forEach { (key: String, value: JsonElement) ->
                    if (value is JsonPrimitive) {
                        value.contentOrNull?.let { this@append.append(key, it) }
                    }
                }
            }

            else -> Unit
        }
    }
}
