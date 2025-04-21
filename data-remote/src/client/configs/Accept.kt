package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiationConfig
import io.ktor.serialization.kotlinx.json.json

internal fun HttpClientConfig<*>.configureAccept() {
    ContentNegotiation {
        json()
    }
}

private fun HttpClientConfig<*>.ContentNegotiation(block: ContentNegotiationConfig.() -> Unit) {
    install(ContentNegotiation, block)
}
