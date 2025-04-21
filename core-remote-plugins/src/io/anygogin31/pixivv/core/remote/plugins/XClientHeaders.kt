package io.anygogin31.pixivv.core.remote.plugins

import io.anygogin31.pixivv.core.remote.constants.HASH_SECRET
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import okio.ByteString.Companion.encodeUtf8

private const val DATE_TIME_PATTERN: String = "uuuu-MM-dd'T'HH:mm:ss'Z'"

@OptIn(FormatStringsInDatetimeFormats::class)
private val UtcFormatter: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    byUnicodePattern(DATE_TIME_PATTERN)
}

private const val HEADER_X_CLIENT_HASH: String = "X-Client-Hash"
private const val HEADER_X_CLIENT_TIME: String = "X-Client-Time"

@OptIn(FormatStringsInDatetimeFormats::class)
public val XClientHeaders: ClientPlugin<Unit> =
    createClientPlugin("XClientHeaders") {
        onRequest { request: HttpRequestBuilder, _ ->
            val utcNow: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            val formattedTime: String = utcNow.format(UtcFormatter)

            val hash: String = (formattedTime + HASH_SECRET)
                .encodeUtf8()
                .md5()
                .hex()

            request.header(HEADER_X_CLIENT_HASH, hash)
            request.header(HEADER_X_CLIENT_TIME, formattedTime)
        }
    }
