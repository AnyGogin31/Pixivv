package io.anygogin31.pixivv.core.remote.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.resources.Resources

public fun HttpClientConfig<*>.configureResources() {
    install(Resources)
}
