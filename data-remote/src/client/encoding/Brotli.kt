package io.anygogin31.pixivv.data.remote.client.encoding

import io.ktor.client.plugins.compression.ContentEncodingConfig

internal expect fun ContentEncodingConfig.brotli(quality: Float? = null)
