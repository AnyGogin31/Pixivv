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

package io.anygogin31.pixivv.screen.walkthrough.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.anygogin31.pixivv.feature.uri.LocalUriLauncher
import io.anygogin31.pixivv.feature.uri.UriLauncher
import io.anygogin31.pixivv.screen.walkthrough.components.ActionButton
import io.anygogin31.pixivv.screen.walkthrough.models.button.Button
import io.anygogin31.pixivv.screen.walkthrough.models.button.ButtonAction
import io.anygogin31.pixivv.screen.walkthrough.models.button.OpenBrowser
import io.anygogin31.pixivv.screen.walkthrough.models.button.Transparent
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPageId

// TODO: https://app-api.pixiv.net/web/v1/provisional-accounts/create
private const val PIXIV_ACCOUNT_CREATE_URL: String = ""

// TODO: https://app-api.pixiv.net/web/v1/login
private const val PIXIV_ACCOUNT_LOGIN_URL: String = ""

internal data object AuthorizationPage : WalkthroughPage {
    override val id: WalkthroughPageId = WalkthroughPageId(4)
    override val title: String = "Authorization"
    override val description: String? = null
    public val buttons: List<Button> =
        listOf(
            Button("Login", OpenBrowser(PIXIV_ACCOUNT_CREATE_URL)),
            Button("Don't have an account?", OpenBrowser(PIXIV_ACCOUNT_LOGIN_URL), Transparent),
        )
}

@Composable
internal fun AuthorizationPageContent(
    data: AuthorizationPage,
    modifier: Modifier = Modifier,
) {
    val uriLauncher: UriLauncher = LocalUriLauncher.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = data.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            data.buttons.forEach { button: Button ->
                ActionButton(
                    button = button,
                    modifier = Modifier.fillMaxWidth(0.75f),
                    onAction = { action: ButtonAction ->
                        if (action is OpenBrowser) {
                            uriLauncher.openUriCustomTabs(action.url)
                        }
                    },
                )
            }
        }
    }
}
