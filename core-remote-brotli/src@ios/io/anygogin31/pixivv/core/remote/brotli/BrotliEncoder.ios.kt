package io.anygogin31.pixivv.core.remote.brotli

import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext

internal actual fun brotliDecode(
    source: ByteReadChannel,
    coroutineContext: CoroutineContext,
): ByteReadChannel {
    error("BrotliInputStream not available")
}
