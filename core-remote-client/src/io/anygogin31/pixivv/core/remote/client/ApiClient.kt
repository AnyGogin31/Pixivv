/*
 * MIT License
 *
 * Copyright (c) 2025 AnyGogin31
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
