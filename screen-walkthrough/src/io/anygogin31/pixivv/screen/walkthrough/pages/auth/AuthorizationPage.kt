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

package io.anygogin31.pixivv.screen.walkthrough.pages.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.anygogin31.pixivv.feature.uri.LocalUriLauncher
import io.anygogin31.pixivv.feature.uri.UriLauncher
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageAction
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageId
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageNode

// TODO: https://app-api.pixiv.net/web/v1/provisional-accounts/create
private const val PIXIV_ACCOUNT_CREATE_URL: String = ""

// TODO: https://app-api.pixiv.net/web/v1/login
private const val PIXIV_ACCOUNT_LOGIN_URL: String = ""

internal data object AuthorizationPage : WalkthroughPageNode {
    override val id: WalkthroughPageId = WalkthroughPageId(4)
    override val next: WalkthroughPageNode? = null
    override var isUnlocked: Boolean = false

    @Composable
    override fun Content(onAction: (action: WalkthroughPageAction) -> Unit) {
        AuthorizationPageContent()
    }
}

@Composable
private fun AuthorizationPageContent(modifier: Modifier = Modifier) {
    val uriLauncher: UriLauncher = LocalUriLauncher.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = "Authorization",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = {
                    uriLauncher.openUriCustomTabs(PIXIV_ACCOUNT_LOGIN_URL)
                },
                modifier = Modifier.fillMaxWidth(0.75f),
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = {
                    uriLauncher.openUriCustomTabs(PIXIV_ACCOUNT_CREATE_URL)
                },
                modifier = Modifier.fillMaxWidth(0.75f),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
            ) {
                Text(text = "Don't have an account?")
            }
        }
    }
}
