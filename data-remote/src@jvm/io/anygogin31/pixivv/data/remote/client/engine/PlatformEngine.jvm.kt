package io.anygogin31.pixivv.data.remote.client.engine

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.jetty.Jetty

internal actual val PlatformEngine: HttpClientEngineFactory<*>
    get() = Jetty
