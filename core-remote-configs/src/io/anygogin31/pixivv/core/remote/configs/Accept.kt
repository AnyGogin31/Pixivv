package io.anygogin31.pixivv.core.remote.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

public fun HttpClientConfig<*>.configureAccept() {
    install(ContentNegotiation) {
        json()
    }
}
