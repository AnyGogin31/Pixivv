package io.anygogin31.pixivv.data.remote.client.configs

import io.anygogin31.pixivv.data.remote.client.encoding.brotli
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.compression.ContentEncoding

internal fun HttpClientConfig<*>.configureEncoding() {
    ContentEncoding {
        brotli(1.0f)
        gzip(0.9f)
    }
}
