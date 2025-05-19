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

public data class NovelModel(
    public val id: Long,
    public val title: String,
    public val caption: String,
    public val restrict: Int,
    public val xRestrict: Int,
    public val isOriginal: Boolean,
    public val imageUrls: ImageUrlsModel,
    public val createDate: Instant,
    public val tags: List<TagModel>,
    public val pageCount: Int,
    public val textLength: Int,
    public val user: UserModel,
    public val series: SeriesModel?,
    public val isBookmarked: Boolean,
    public val totalBookmarks: Int,
    public val totalView: Int,
    public val visible: Boolean,
    public val totalComments: Int,
    public val isMuted: Boolean,
    public val isMypixivOnly: Boolean,
    public val isXRestricted: Boolean,
    public val novelAiType: Int,
)
