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

package io.anygogin31.pixivv.data.remote.clients.pixiv

import io.anygogin31.pixivv.core.remote.client.ApiClient
import io.anygogin31.pixivv.core.remote.client.apiClient
import io.anygogin31.pixivv.core.remote.configs.configureAccept
import io.anygogin31.pixivv.core.remote.configs.configureCharsets
import io.anygogin31.pixivv.core.remote.configs.configureDefault
import io.anygogin31.pixivv.core.remote.configs.configureEncoding
import io.anygogin31.pixivv.core.remote.configs.configureLogging
import io.anygogin31.pixivv.core.remote.configs.configureResources
import io.anygogin31.pixivv.core.remote.configs.configureRetry
import io.anygogin31.pixivv.core.remote.configs.configureXClient
import io.anygogin31.pixivv.core.remote.constants.PIXIV_API_URL
import io.ktor.client.HttpClientConfig

internal object PixivApiClient : ApiClient by apiClient(::usePixivConfigs)

private fun usePixivConfigs(config: HttpClientConfig<*>) {
    with(config) {
        configureAccept()
        configureCharsets()
        configureDefault(PIXIV_API_URL)
        configureEncoding()
        configureLogging()
        configurePixivAuth()
        configureResources()
        configureRetry()
        configureXClient()
    }
}
