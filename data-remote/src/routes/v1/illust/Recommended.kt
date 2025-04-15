package io.anygogin31.pixivv.data.remote.routes.v1.illust

import io.anygogin31.pixivv.data.remote.routes.V1
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/illust/recommended")
public data class Recommended(
    public val parent: V1 = V1,
    @SerialName("filter")
    public val contentFilter: String = "for_android",
    @SerialName("include_ranking_illusts")
    public val includeRankingIllustrations: Boolean = false,
    @SerialName("include_privacy_policy")
    public val includePrivacyPolicy: Boolean = false,
)
