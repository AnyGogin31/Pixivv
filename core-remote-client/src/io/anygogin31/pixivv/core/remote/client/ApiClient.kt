package io.anygogin31.pixivv.core.remote.client

import io.anygogin31.pixivv.core.remote.engine.Pixivv
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

private typealias ClientConfig = HttpClientConfig<*>.() -> Unit
private typealias ClientRequest = suspend HttpClient.() -> HttpResponse

public interface ApiClient {
    public val client: HttpClient
}

public fun apiClient(config: ClientConfig = {}): ApiClient {
    return object : ApiClient {
        override val client: HttpClient by lazy {
            HttpClient(Pixivv, config)
        }
    }
}

public suspend inline fun <reified T : Any> ApiClient.request(
    crossinline block: ClientRequest,
): Result<T> {
    return runCatching {
        client.block().body<T>()
    }
}
