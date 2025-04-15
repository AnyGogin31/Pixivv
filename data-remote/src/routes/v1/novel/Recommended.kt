package io.anygogin31.pixivv.data.remote.routes.v1.novel

import io.anygogin31.pixivv.data.remote.routes.V1
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/novel/recommended")
public data class Recommended(
    public val parent: V1 = V1,
    @SerialName("include_ranking_novels")
    public val includeRankingNovels: Boolean = false,
    @SerialName("include_privacy_policy")
    public val includePrivacyPolicy: Boolean = false,
    @SerialName("view_novel_ids[]")
    public val viewedNovelIds: List<Long> = emptyList(),
    @SerialName("read_novel_datetimes[]")
    public val readNovelDatetimes: List<String> = emptyList(),
    @SerialName("view_novel_datetimes[]")
    public val viewedNovelDatetimes: List<String> = emptyList(),
)
