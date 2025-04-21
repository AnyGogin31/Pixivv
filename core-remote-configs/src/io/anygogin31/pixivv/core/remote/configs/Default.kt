package io.anygogin31.pixivv.core.remote.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest

public fun HttpClientConfig<*>.configureDefault(urlString: String) {
    install(DefaultRequest) {
        url(urlString)
    }
}
