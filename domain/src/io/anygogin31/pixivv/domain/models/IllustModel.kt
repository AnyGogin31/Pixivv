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

package io.anygogin31.pixivv.domain.models

import kotlinx.datetime.Instant

public data class IllustModel(
    public val id: Long,
    public val title: String,
    public val type: String,
    public val imageUrls: ImageUrlsModel,
    public val caption: String,
    public val restrict: Int,
    public val user: UserModel,
    public val tags: List<TagModel>,
    public val tools: List<String>,
    public val createDate: Instant,
    public val pageCount: Int,
    public val width: Int,
    public val height: Int,
    public val sanityLevel: Int,
    public val xRestrict: Int,
    public val series: SeriesModel?,
    public val metaSinglePage: MetaSinglePageModel,
    public val metaPages: List<MetaPageModel>,
    public val totalView: Int,
    public val totalBookmarks: Int,
    public val isBookmarked: Boolean,
    public val visible: Boolean,
    public val isMuted: Boolean,
    public val illustAiType: Int,
    public val illustBookStyle: Int,
)
