package io.anygogin31.pixivv.data.remote.clients.pixiv

import io.anygogin31.pixivv.core.remote.auth.providers.PixivAuthProvider
import io.anygogin31.pixivv.core.remote.auth.sendWithoutRequest
import io.anygogin31.pixivv.core.remote.utils.append
import io.anygogin31.pixivv.data.remote.models.requests.pixiv.RefreshTokenRequest
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.AuthResponse
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.IdpUrlsResponse
import io.anygogin31.pixivv.data.remote.services.pixiv.getIdpUrls
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.forms.submitForm
import io.ktor.http.Parameters
import org.koin.mp.KoinPlatform

internal fun HttpClientConfig<*>.configurePixivAuth() {
    val pixivAuthProvider: PixivAuthProvider = KoinPlatform.getKoin().get()
    install(Auth) {
        bearer {
            loadTokens {
                pixivAuthProvider.loadTokens().getOrNull()
            }
            refreshTokens {
                val refreshToken: String = this.oldTokens?.refreshToken
                    ?: return@refreshTokens null

                val idpUrls: IdpUrlsResponse = PixivApiClient.getIdpUrls().getOrNull()
                    ?: return@refreshTokens null

                val authResponse: AuthResponse = this.client
                    .submitForm(
                        url = idpUrls.authToken,
                        formParameters = Parameters.build {
                            append(RefreshTokenRequest.fromRefreshToken(refreshToken))
                        }
                    ) {
                        markAsRefreshTokenRequest()
                    }
                    .body()

                pixivAuthProvider
                    .saveTokens(
                        accessToken = authResponse.accessToken,
                        refreshToken = authResponse.refreshToken,
                    )
                    .getOrNull()
                    ?: return@refreshTokens null

                BearerTokens(authResponse.accessToken, authResponse.refreshToken)
            }
            sendWithoutRequest(pixivAuthProvider::sendWithoutRequest)
        }
    }
}
