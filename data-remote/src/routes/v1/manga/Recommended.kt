package io.anygogin31.pixivv.data.remote.routes.v1.manga

import io.anygogin31.pixivv.data.remote.routes.V1
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/manga/recommended")
public data class Recommended(
    public val parent: V1 = V1,
    @SerialName("filter")
    public val contentFilter: String = "for_android",
    @SerialName("include_ranking_illusts")
    public val includeRankingManga: Boolean = false,
    @SerialName("include_privacy_policy")
    public val includePrivacyPolicy: Boolean = false,
)
