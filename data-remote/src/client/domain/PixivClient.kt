package io.anygogin31.pixivv.data.remote.client.domain

import io.anygogin31.pixivv.data.remote.RemoteConstants
import io.anygogin31.pixivv.data.remote.client.RemoteClient
import io.anygogin31.pixivv.data.remote.client.engine.PlatformEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest

public object PixivClient : RemoteClient {
    override val client: HttpClient by lazy {
        HttpClient(PlatformEngine) {
            defaultRequest {
                url(RemoteConstants.PIXIV_BASE_URL)
            }
        }
    }
}
