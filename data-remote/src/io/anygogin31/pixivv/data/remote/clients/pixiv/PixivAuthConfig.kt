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
