package io.anygogin31.pixivv.core.remote.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpPlainText
import io.ktor.utils.io.charsets.Charsets

public fun HttpClientConfig<*>.configureCharsets() {
    install(HttpPlainText) {
        register(Charsets.UTF_8)
    }
}
