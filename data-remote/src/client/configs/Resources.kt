package io.anygogin31.pixivv.data.remote.client.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.resources.Resources

internal fun HttpClientConfig<*>.configureResources() {
    install(Resources)
}
