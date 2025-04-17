package io.anygogin31.pixivv.data.remote.requests.pixiv

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

private const val CLIENT_ID: String = "MOBrBDS8blbauoSck0ZfDbtuzpyT"
private const val CLIENT_SECRET: String = "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj"
