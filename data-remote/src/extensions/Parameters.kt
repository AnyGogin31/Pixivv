package io.anygogin31.pixivv.data.remote.extensions

import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.encodeToJsonElement

private val DefaultJson: Json = Json { encodeDefaults = true }

internal inline fun <reified T : Any> parametersOf(value: T): Parameters {
    val json: Json = DefaultJson
    val jsonElement: JsonElement = json.encodeToJsonElement(value)
    val builder = ParametersBuilder()
    jsonElement.toParameters(builder)
    return builder.build()
}

private fun JsonElement.toParameters(builder: ParametersBuilder) {
    when (this) {
        is JsonObject -> {
            entries.forEach { (key: String, value: JsonElement) ->
                if (value is JsonPrimitive) {
                    value.contentOrNull?.let { builder.append(key, it) }
                }
            }
        }

        else -> Unit
    }
}
