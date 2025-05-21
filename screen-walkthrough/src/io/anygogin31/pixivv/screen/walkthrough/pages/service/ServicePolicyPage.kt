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

package io.anygogin31.pixivv.screen.walkthrough.pages.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.anygogin31.pixivv.core.remote.constants.PIXIV_SERVICE_POLICY_URL
import io.anygogin31.pixivv.feature.uri.LocalUriLauncher
import io.anygogin31.pixivv.feature.uri.UriLauncher
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageId
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageNode
import io.anygogin31.pixivv.screen.walkthrough.pages.client.ClientPolicyPage

internal data object ServicePolicyPage : WalkthroughPageNode {
    override val id: WalkthroughPageId = WalkthroughPageId(2)
    override val next: WalkthroughPageNode = ClientPolicyPage
    override var isUnlocked: Boolean = true
        set(_) = Unit

    @Composable
    override fun Content() {
        ServicePolicyPageContent()
    }
}

@Composable
private fun ServicePolicyPageContent(
    modifier: Modifier = Modifier,
    onAgree: () -> Unit = {},
) {
    val uriLauncher: UriLauncher = LocalUriLauncher.current
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = "Privacy Policy",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Before using the Pixiv service, please review their privacy policy",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }

        Row(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {
                    uriLauncher.openUriCustomTabs(PIXIV_SERVICE_POLICY_URL)
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Read")
            }

            Button(
                onClick = onAgree,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "I Agree")
            }
        }
    }
}
