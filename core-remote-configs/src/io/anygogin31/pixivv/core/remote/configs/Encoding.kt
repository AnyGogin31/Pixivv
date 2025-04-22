package io.anygogin31.pixivv.core.remote.configs

import io.anygogin31.pixivv.core.remote.brotli.brotli
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.compression.ContentEncoding

public fun HttpClientConfig<*>.configureEncoding() {
    install(ContentEncoding) {
        brotli(1.0f)
        gzip(0.9f)
    }
}
