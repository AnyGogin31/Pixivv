package io.anygogin31.pixivv.data.remote.models.responses.pixiv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IdpUrlsResponse(
    @SerialName("account-edit")
    public val accountEdit: String,
    @SerialName("account-leave-prepare")
    public val accountLeavePrepare: String,
    @SerialName("account-leave-status")
    public val accountLeaveStatus: String,
    @SerialName("account-setting-prepare")
    public val accountSettingPrepare: String,
    @SerialName("auth-token")
    public val authToken: String,
    @SerialName("auth-token-redirect-uri")
    public val authTokenRedirectUri: String,
)
