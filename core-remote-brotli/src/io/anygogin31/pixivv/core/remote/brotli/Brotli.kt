package io.anygogin31.pixivv.core.remote.brotli

import io.ktor.client.plugins.compression.ContentEncodingConfig

public expect fun ContentEncodingConfig.brotli(quality: Float? = null)
