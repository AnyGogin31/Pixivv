package io.anygogin31.pixivv.core.remote.brotli

import io.ktor.client.plugins.compression.ContentEncodingConfig

public actual fun ContentEncodingConfig.brotli(quality: Float?) = Unit
