package io.anygogin31.pixivv.data.remote.client.session

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.anygogin31.pixivv.core.storage.domain.SessionKeyValueStorage
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal object BearerTokenProvider : KoinComponent {
    private val ACCESS_TOKEN_KEY: Preferences.Key<String> = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN_KEY: Preferences.Key<String> = stringPreferencesKey("refresh_token")

    private val sessionKeyValueStorage: SessionKeyValueStorage = get()

    internal suspend fun loadTokens(): BearerTokens? {
        return runCatching {
            val accessToken: String = sessionKeyValueStorage.getOrThrow(ACCESS_TOKEN_KEY)
            val refreshToken: String? = sessionKeyValueStorage.getOrNull(REFRESH_TOKEN_KEY)
            BearerTokens(accessToken, refreshToken)
        }.getOrNull()
    }

    internal suspend fun refreshTokens(): BearerTokens? {
        return null // TODO
    }
}
