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

package io.anygogin31.pixivv.data.remote.models.responses.pixiv

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class NovelResponse(
    @SerialName("id")
    public val id: Long,
    @SerialName("title")
    public val title: String,
    @SerialName("caption")
    public val caption: String,
    @SerialName("restrict")
    public val restrict: Int,
    @SerialName("x_restrict")
    public val xRestrict: Int,
    @SerialName("is_original")
    public val isOriginal: Boolean,
    @SerialName("image_urls")
    public val imageUrls: ImageUrlsResponse,
    @SerialName("create_date")
    public val createDate: Instant,
    @SerialName("tags")
    public val tags: List<TagResponse>,
    @SerialName("page_count")
    public val pageCount: Int,
    @SerialName("text_length")
    public val textLength: Int,
    @SerialName("user")
    public val user: UserResponse,
    @SerialName("series")
    public val series: SeriesResponse?,
    @SerialName("is_bookmarked")
    public val isBookmarked: Boolean,
    @SerialName("total_bookmarks")
    public val totalBookmarks: Int,
    @SerialName("total_view")
    public val totalView: Int,
    @SerialName("visible")
    public val visible: Boolean,
    @SerialName("total_comments")
    public val totalComments: Int,
    @SerialName("is_muted")
    public val isMuted: Boolean,
    @SerialName("is_mypixiv_only")
    public val isMypixivOnly: Boolean,
    @SerialName("is_x_restricted")
    public val isXRestricted: Boolean,
    @SerialName("novel_ai_type")
    public val novelAiType: Int,
)
