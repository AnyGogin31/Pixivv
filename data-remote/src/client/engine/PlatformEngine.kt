package io.anygogin31.pixivv.data.remote.client.engine

import io.ktor.client.engine.HttpClientEngineFactory

internal expect val PlatformEngine: HttpClientEngineFactory<*>
