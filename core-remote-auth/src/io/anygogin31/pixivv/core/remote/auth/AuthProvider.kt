package io.anygogin31.pixivv.core.remote.auth

import io.anygogin31.pixivv.core.remote.auth.attributes.SkipAuth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.HttpRequestBuilder

public interface AuthProvider

public interface BearerAuthProvider : AuthProvider {
    public suspend fun loadTokens(): Result<BearerTokens>
    public suspend fun saveTokens(accessToken: String, refreshToken: String): Result<Unit>
}

public fun AuthProvider.sendWithoutRequest(request: HttpRequestBuilder): Boolean {
    return request.attributes.contains(SkipAuth)
}
