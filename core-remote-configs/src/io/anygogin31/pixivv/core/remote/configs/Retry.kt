package io.anygogin31.pixivv.core.remote.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry

public fun HttpClientConfig<*>.configureRetry() {
    install(HttpRequestRetry) {
        maxRetries = 3
        exponentialDelay()
        retryOnExceptionOrServerErrors()
    }
}
