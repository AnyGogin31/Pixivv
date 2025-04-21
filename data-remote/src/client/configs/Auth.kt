package io.anygogin31.pixivv.data.remote.client.configs

import io.anygogin31.pixivv.data.remote.client.session.BearerTokenProvider
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer

internal fun HttpClientConfig<*>.configureAuth() {
    Auth {
        bearer {
            loadTokens(BearerTokenProvider::loadTokens)
            refreshTokens(BearerTokenProvider::refreshTokens)
        }
    }
}
