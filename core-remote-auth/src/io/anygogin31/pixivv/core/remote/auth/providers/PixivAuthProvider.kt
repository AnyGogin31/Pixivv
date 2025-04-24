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

package io.anygogin31.pixivv.core.remote.auth.providers

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import io.anygogin31.pixivv.core.remote.auth.BearerAuthProvider
import io.anygogin31.pixivv.core.storage.domain.SessionPixivKeyValueStorage
import io.ktor.client.plugins.auth.providers.BearerTokens

public class PixivAuthProvider(
    private val sessionKeyValueStorage: SessionPixivKeyValueStorage,
) : BearerAuthProvider {
    private val accessTokenKey: Preferences.Key<String> = stringPreferencesKey("access_token")
    private val refreshTokenKey: Preferences.Key<String> = stringPreferencesKey("refresh_token")
    private val tokensReadyKey: Preferences.Key<Boolean> = booleanPreferencesKey("tokens_ready")

    override suspend fun loadTokens(): Result<BearerTokens> {
        return runCatching {
            val isReady = sessionKeyValueStorage.getOrNull(tokensReadyKey) ?: false
            check(isReady) { "Tokens were not fully saved, skipping load." }

            val accessToken: String = sessionKeyValueStorage.getOrThrow(accessTokenKey)
            val refreshToken: String = sessionKeyValueStorage.getOrThrow(refreshTokenKey)

            check(accessToken.isNotBlank()) { "Access token is blank." }
            check(refreshToken.isNotBlank()) { "Refresh token is blank." }

            BearerTokens(accessToken, refreshToken)
        }
    }

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ): Result<Unit> {
        return runCatching {
            check(accessToken.isNotBlank()) { "Access token cannot be blank." }
            check(refreshToken.isNotBlank()) { "Refresh token cannot be blank." }

            sessionKeyValueStorage.set(tokensReadyKey, false).getOrThrow()

            sessionKeyValueStorage.set(accessTokenKey, accessToken).getOrThrow()
            sessionKeyValueStorage.set(refreshTokenKey, refreshToken).getOrThrow()

            sessionKeyValueStorage.set(tokensReadyKey, true).getOrThrow()
        }
    }
}
