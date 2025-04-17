package io.anygogin31.pixivv.data.remote.responses.pixiv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AuthResponse(
    @SerialName("access_token")
    public val accessToken: String,
    @SerialName("refresh_token")
    public val refreshToken: String,
)
