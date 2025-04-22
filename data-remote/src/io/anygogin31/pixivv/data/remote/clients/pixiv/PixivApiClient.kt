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
