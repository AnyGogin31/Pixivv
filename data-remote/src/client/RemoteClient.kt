package io.anygogin31.pixivv.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

public interface RemoteClient {
    public val client: HttpClient
}

internal suspend inline fun <reified T : Any> RemoteClient.request(
    crossinline action: suspend HttpClient.() -> HttpResponse,
): Result<T> {
    return runCatching {
        client.action().body<T>()
    }
}
