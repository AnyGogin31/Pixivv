package io.anygogin31.pixivv.data.remote.client.domain

import io.anygogin31.pixivv.data.remote.PIXIV_BASE_URL
import io.anygogin31.pixivv.data.remote.client.RemoteClient
import io.anygogin31.pixivv.data.remote.client.configs.configureAccept
import io.anygogin31.pixivv.data.remote.client.configs.configureAuth
import io.anygogin31.pixivv.data.remote.client.configs.configureCharsets
import io.anygogin31.pixivv.data.remote.client.configs.configureDefault
import io.anygogin31.pixivv.data.remote.client.configs.configureEncoding
import io.anygogin31.pixivv.data.remote.client.configs.configureLogging
import io.anygogin31.pixivv.data.remote.client.configs.configureResources
import io.anygogin31.pixivv.data.remote.client.configs.configureRetry
import io.anygogin31.pixivv.data.remote.client.configs.configureXClient
import io.anygogin31.pixivv.data.remote.client.engine.PlatformEngine
import io.ktor.client.HttpClient

public object PixivClient : RemoteClient {
    override val client: HttpClient by lazy {
        HttpClient(PlatformEngine) {
            configureAccept()
            configureAuth()
            configureCharsets()
            configureDefault(PIXIV_BASE_URL)
            configureEncoding()
            configureLogging()
            configureResources()
            configureRetry()
            configureXClient()
        }
    }
}
