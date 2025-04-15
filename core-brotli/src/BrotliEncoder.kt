package io.anygogin31.pixivv.core.brotli

import io.ktor.util.ContentEncoder
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.jvm.javaio.toByteReadChannel
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlin.coroutines.CoroutineContext
import org.brotli.dec.BrotliInputStream

public object BrotliEncoder : ContentEncoder {
    override val name: String = "br"

    override fun encode(
        source: ByteReadChannel,
        coroutineContext: CoroutineContext,
    ): ByteReadChannel {
        error("BrotliOutputStream not available (<https://github.com/google/brotli/issues/715>)")
    }

    override fun encode(
        source: ByteWriteChannel,
        coroutineContext: CoroutineContext,
    ): ByteWriteChannel {
        error("BrotliOutputStream not available (<https://github.com/google/brotli/issues/715>)")
    }

    override fun decode(
        source: ByteReadChannel,
        coroutineContext: CoroutineContext,
    ): ByteReadChannel {
        return BrotliInputStream(source.toInputStream()).toByteReadChannel(context = coroutineContext)
    }
}
