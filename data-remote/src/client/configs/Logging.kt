package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders

internal fun HttpClientConfig<*>.configureLogging() {
    Logging {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
        sanitizeHeader { header: String ->
            header == HttpHeaders.Authorization
        }
    }
}
