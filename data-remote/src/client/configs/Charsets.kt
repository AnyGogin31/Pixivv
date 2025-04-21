package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.Charsets
import io.ktor.utils.io.charsets.Charsets

internal fun HttpClientConfig<*>.configureCharsets() {
    Charsets {
        register(Charsets.UTF_8)
    }
}
