package io.anygogin31.pixivv.data.remote.services.pixiv

import io.anygogin31.pixivv.core.remote.auth.attributes.SkipAuth
import io.anygogin31.pixivv.core.remote.client.request
import io.anygogin31.pixivv.data.remote.clients.pixiv.PixivApiClient
import io.anygogin31.pixivv.data.remote.models.responses.pixiv.IdpUrlsResponse
import io.anygogin31.pixivv.data.remote.routes.pixiv.IdpUrls
import io.ktor.client.plugins.resources.get

private var cachedIdpUrls: IdpUrlsResponse? = null

internal suspend fun PixivApiClient.getIdpUrls(): Result<IdpUrlsResponse> {
    cachedIdpUrls?.let { return Result.success(it) }

    val request: Result<IdpUrlsResponse> = request {
        get(IdpUrls) {
            attributes.put(SkipAuth, Unit)
        }
    }

    return request.onSuccess { cachedIdpUrls = it }
}
