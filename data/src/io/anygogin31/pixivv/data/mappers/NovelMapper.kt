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

package io.anygogin31.pixivv.data.mappers

import io.anygogin31.pixivv.data.remote.models.responses.pixiv.NovelResponse
import io.anygogin31.pixivv.domain.models.NovelModel

internal fun NovelResponse.toDomain(): NovelModel {
    return NovelModel(
        id = id,
        title = title,
        caption = caption,
        restrict = restrict,
        xRestrict = xRestrict,
        isOriginal = isOriginal,
        imageUrls = imageUrls.toDomain(),
        tags = tags.map { it.toDomain() },
        createDate = createDate,
        pageCount = pageCount,
        textLength = textLength,
        user = user.toDomain(),
        series = series?.toDomain(),
        isBookmarked = isBookmarked,
        totalBookmarks = totalBookmarks,
        totalView = totalView,
        visible = visible,
        totalComments = totalComments,
        isMuted = isMuted,
        isMypixivOnly = isMypixivOnly,
        isXRestricted = isXRestricted,
        novelAiType = novelAiType,
    )
}
