package io.anygogin31.pixivv.data.remote.client.encoding

import io.ktor.client.plugins.compression.ContentEncodingConfig

internal actual fun ContentEncodingConfig.brotli(quality: Float?) = Unit
