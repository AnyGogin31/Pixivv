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

package io.anygogin31.pixivv.data.remote.models.requests.pixiv

import io.anygogin31.pixivv.core.remote.constants.CLIENT_ID
import io.anygogin31.pixivv.core.remote.constants.CLIENT_SECRET
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RefreshTokenRequest(
    @SerialName("client_id")
    private val clientId: String = CLIENT_ID,
    @SerialName("client_secret")
    private val clientSecret: String = CLIENT_SECRET,
    @SerialName("grant_type")
    public val grantType: String = GRANT_TYPE,
    @SerialName("refresh_token")
    private val refreshToken: String,
    @SerialName("include_policy")
    public val includePolicy: Boolean = false,
) {
    public companion object {
        private const val GRANT_TYPE: String = "refresh_token"

        public fun fromRefreshToken(refreshToken: String): RefreshTokenRequest {
            return RefreshTokenRequest(refreshToken = refreshToken)
        }
    }
}
