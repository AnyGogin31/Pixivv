package io.anygogin31.pixivv.data.remote.client.domain

import io.anygogin31.pixivv.data.remote.client.RemoteClient
import io.ktor.client.HttpClient

public object PixivClient : RemoteClient {
    override val client: HttpClient
        get() = TODO("Not yet implemented")
}
