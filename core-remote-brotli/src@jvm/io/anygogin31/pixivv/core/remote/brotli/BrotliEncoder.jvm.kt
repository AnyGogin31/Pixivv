package io.anygogin31.pixivv.core.remote.brotli

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.toByteReadChannel
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlin.coroutines.CoroutineContext
import org.brotli.dec.BrotliInputStream

internal actual fun brotliDecode(
    source: ByteReadChannel,
    coroutineContext: CoroutineContext,
): ByteReadChannel {
    return BrotliInputStream(source.toInputStream())
        .toByteReadChannel(context = coroutineContext)
}
