package io.anygogin31.pixivv.data.remote.client.plugins

import io.anygogin31.pixivv.core.remote.constants.HASH_SECRET
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import okio.ByteString.Companion.encodeUtf8

private const val DATE_TIME_PATTERN: String = "uuuu-MM-dd'T'HH:mm:ss'Z'"

private val HttpHeaders.XClientHash: String
    get() = "X-Client-Hash"

private val HttpHeaders.XClientTime: String
    get() = "X-Client-Time"

@OptIn(FormatStringsInDatetimeFormats::class)
internal val XClientHeadersPlugin: ClientPlugin<Unit> =
    createClientPlugin("XClientHeaders") {
        onRequest { request: HttpRequestBuilder, _ ->
            val utcNow: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            val formattedTime: String = utcNow.format(
                LocalDateTime.Format {
                    byUnicodePattern(DATE_TIME_PATTERN)
                }
            )

            val hash: String = (formattedTime + HASH_SECRET)
                .encodeUtf8()
                .md5()
                .hex()

            request.header(HttpHeaders.XClientHash, hash)
            request.header(HttpHeaders.XClientTime, formattedTime)
        }
    }
