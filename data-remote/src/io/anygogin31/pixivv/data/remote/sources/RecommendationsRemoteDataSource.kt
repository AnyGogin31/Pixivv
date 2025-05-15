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

package io.anygogin31.pixivv.data.remote.sources

import io.anygogin31.pixivv.data.remote.clients.pixiv.PixivApiClient
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.RecommendedIllustResponse
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.RecommendedMangaResponse
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.RecommendedNovelResponse
import io.anygogin31.pixivv.data.remote.services.pixiv.v1.getRecommendedIllust
import io.anygogin31.pixivv.data.remote.services.pixiv.v1.getRecommendedManga
import io.anygogin31.pixivv.data.remote.services.pixiv.v1.getRecommendedNovel

public class RecommendationsRemoteDataSource {
    public suspend fun getRecommendedIllust(): Result<RecommendedIllustResponse> {
        return PixivApiClient.getRecommendedIllust()
    }

    public suspend fun getRecommendedManga(): Result<RecommendedMangaResponse> {
        return PixivApiClient.getRecommendedManga()
    }

    public suspend fun getRecommendedNovel(): Result<RecommendedNovelResponse> {
        return PixivApiClient.getRecommendedNovel()
    }
}
