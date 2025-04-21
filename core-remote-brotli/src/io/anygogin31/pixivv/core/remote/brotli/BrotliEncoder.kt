package io.anygogin31.pixivv.core.remote.brotli

import io.ktor.util.ContentEncoder
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.CoroutineContext

internal object BrotliEncoder : ContentEncoder {
    override val name: String = "br"

    override fun encode(
        source: ByteReadChannel,
        coroutineContext: CoroutineContext,
    ): ByteReadChannel {
        brotliEncode()
    }

    override fun encode(
        source: ByteWriteChannel,
        coroutineContext: CoroutineContext,
    ): ByteWriteChannel {
        brotliEncode()
    }

    override fun decode(
        source: ByteReadChannel,
        coroutineContext: CoroutineContext,
    ): ByteReadChannel {
        return brotliDecode(source, coroutineContext)
    }
}

internal fun brotliEncode(): Nothing {
    error("BrotliOutputStream not available (https://github.com/google/brotli/issues/715)")
}

internal expect fun brotliDecode(source: ByteReadChannel, coroutineContext: CoroutineContext): ByteReadChannel
