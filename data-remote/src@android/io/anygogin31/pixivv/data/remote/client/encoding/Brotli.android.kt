package io.anygogin31.pixivv.data.remote.client.encoding

import io.anygogin31.pixivv.core.brotli.BrotliEncoder
import io.ktor.client.plugins.compression.ContentEncodingConfig

internal actual fun ContentEncodingConfig.brotli(quality: Float?) {
    customEncoder(BrotliEncoder, quality)
}
