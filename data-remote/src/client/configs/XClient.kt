package io.anygogin31.pixivv.data.remote.client.configs

import io.anygogin31.pixivv.core.remote.plugins.XClientHeaders
import io.ktor.client.HttpClientConfig

internal fun HttpClientConfig<*>.configureXClient() {
    install(XClientHeaders)
}
