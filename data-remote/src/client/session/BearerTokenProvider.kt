package io.anygogin31.pixivv.data.remote.client.session

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.anygogin31.pixivv.core.storage.domain.SessionKeyValueStorage
import io.anygogin31.pixivv.data.remote.client.domain.PixivClient
import io.anygogin31.pixivv.data.remote.extensions.parametersOf
import io.anygogin31.pixivv.data.remote.requests.pixiv.RefreshTokenRequest
import io.anygogin31.pixivv.data.remote.responses.pixiv.AuthResponse
import io.anygogin31.pixivv.data.remote.responses.pixiv.IdpUrlsResponse
import io.anygogin31.pixivv.data.remote.services.pixiv.getIdpUrls
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.RefreshTokensParams
import io.ktor.client.request.forms.submitForm
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

    internal suspend fun refreshTokens(params: RefreshTokensParams): BearerTokens? {
        return runCatching {
            val refreshToken: String = params.oldTokens?.refreshToken
                ?: return null

            val idpUrls: IdpUrlsResponse = PixivClient.getIdpUrls().getOrNull()
                ?: return null

            val authResponse: AuthResponse = params.client
                .submitForm(
                    url = idpUrls.authToken,
                    formParameters = parametersOf(
                        RefreshTokenRequest.fromRefreshToken(refreshToken),
                    )
                ).body()

            sessionKeyValueStorage.set(ACCESS_TOKEN_KEY, authResponse.accessToken)
            sessionKeyValueStorage.set(REFRESH_TOKEN_KEY, authResponse.refreshToken)

            BearerTokens(authResponse.accessToken, authResponse.refreshToken)
        }.getOrNull()
    }
}
