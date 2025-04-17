package io.anygogin31.pixivv.data.remote.services.pixiv

import io.anygogin31.pixivv.data.remote.client.domain.PixivClient
import io.anygogin31.pixivv.data.remote.client.request
import io.anygogin31.pixivv.data.remote.responses.pixiv.IdpUrlsResponse
import io.anygogin31.pixivv.data.remote.routes.pixiv.IdpUrls
import io.ktor.client.plugins.resources.get

private var cachedIdpUrls: IdpUrlsResponse? = null

public suspend fun PixivClient.getIdpUrls(): Result<IdpUrlsResponse> {
    cachedIdpUrls?.let { return Result.success(it) }

    val request: Result<IdpUrlsResponse> = request {
        get(IdpUrls)
    }

    return request.onSuccess { cachedIdpUrls = it }
}
