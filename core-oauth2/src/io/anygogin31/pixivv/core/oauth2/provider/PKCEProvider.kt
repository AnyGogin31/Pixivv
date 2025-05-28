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

package io.anygogin31.pixivv.core.oauth2.provider

import okio.ByteString.Companion.encodeUtf8

public interface PKCEProvider {
    public companion object {
        public const val CODE_VERIFIER_LENGTH: Int = 32
    }

    public fun getCodeChallenge(): String

    public fun getCodeVerifier(): String
}

internal class DefaultPKCEProvider : PKCEProvider {
    private var codeVerifier: String? = null

    override fun getCodeChallenge(): String {
        val codeVerifier: String = getCodeVerifier()
        val codeChallenge: String =
            codeVerifier
                .encodeUtf8()
                .sha256()
                .base64Url()
                .trimEnd('=')
        return codeChallenge
    }

    override fun getCodeVerifier(): String = codeVerifier ?: createCodeVerifier().also { codeVerifier = it }

    private fun createCodeVerifier(): String = _createCodeVerifier()
}

internal expect fun _createCodeVerifier(): String
