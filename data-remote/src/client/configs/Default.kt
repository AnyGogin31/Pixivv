package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.defaultRequest

internal fun HttpClientConfig<*>.configureDefault(urlString: String) {
    defaultRequest {
        url(urlString)
    }
}
