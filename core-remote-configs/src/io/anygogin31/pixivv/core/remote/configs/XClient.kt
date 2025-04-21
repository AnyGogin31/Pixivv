package io.anygogin31.pixivv.core.remote.configs

import io.anygogin31.pixivv.core.remote.plugins.XClientHeaders
import io.ktor.client.HttpClientConfig

public fun HttpClientConfig<*>.configureXClient() {
    install(XClientHeaders)
}
