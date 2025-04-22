package io.anygogin31.pixivv.core.remote.engine

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.jetty.Jetty

public actual val Pixivv: HttpClientEngine by lazy {
    Jetty.create()
}
