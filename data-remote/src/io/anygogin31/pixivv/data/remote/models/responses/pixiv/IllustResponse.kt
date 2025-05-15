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
public data class IllustResponse(
    @SerialName("id")
    public val id: Long,
    @SerialName("title")
    public val title: String,
    @SerialName("type")
    public val type: String,
    @SerialName("image_urls")
    public val imageUrls: ImageUrlsResponse,
    @SerialName("caption")
    public val caption: String,
    @SerialName("restrict")
    public val restrict: Int,
    @SerialName("user")
    public val user: UserResponse,
    @SerialName("tags")
    public val tags: List<TagResponse>,
    @SerialName("tools")
    public val tools: List<String>,
    @SerialName("create_date")
    public val createDate: Instant,
    @SerialName("page_count")
    public val pageCount: Int,
    @SerialName("width")
    public val width: Int,
    @SerialName("height")
    public val height: Int,
    @SerialName("sanity_level")
    public val sanityLevel: Int,
    @SerialName("x_restrict")
    public val xRestrict: Int,
    @SerialName("series")
    public val series: SeriesResponse?,
    @SerialName("meta_single_page")
    public val metaSinglePage: MetaSinglePageResponse,
    @SerialName("meta_pages")
    public val metaPages: List<MetaPageResponse>,
    @SerialName("total_view")
    public val totalView: Int,
    @SerialName("total_bookmarks")
    public val totalBookmarks: Int,
    @SerialName("is_bookmarked")
    public val isBookmarked: Boolean,
    @SerialName("visible")
    public val visible: Boolean,
    @SerialName("is_muted")
    public val isMuted: Boolean,
    @SerialName("illust_ai_type")
    public val illustAiType: Int,
    @SerialName("illust_book_style")
    public val illustBookStyle: Int,
)
