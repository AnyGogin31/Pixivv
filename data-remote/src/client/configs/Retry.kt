package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpRequestRetryConfig

internal fun HttpClientConfig<*>.configureRetry() {
    HttpRequestRetry {
        maxRetries = 3
        exponentialDelay()
        retryOnExceptionOrServerErrors()
    }
}

private fun HttpClientConfig<*>.HttpRequestRetry(block: HttpRequestRetryConfig.() -> Unit) {
    install(HttpRequestRetry, block)
}
