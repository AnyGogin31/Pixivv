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

package io.anygogin31.pixivv.data.remote.routes.pixiv.v1.novel

import io.anygogin31.pixivv.data.remote.routes.V1
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/novel/recommended")
internal data class Recommended(
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
