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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.anygogin31.pixivv.screen.walkthrough.components.ActionButton
import io.anygogin31.pixivv.screen.walkthrough.models.button.Back
import io.anygogin31.pixivv.screen.walkthrough.models.button.Button
import io.anygogin31.pixivv.screen.walkthrough.models.button.ButtonAction
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPageId

internal data object LockedPage : WalkthroughPage {
    override val id: WalkthroughPageId = WalkthroughPageId(0)
    override val title: String = "Locked Content"
    override val description: String = "Please complete previous steps to unlock this content"
    public val buttons: List<Button> =
        listOf(
            Button("Back", Back),
        )
}

@Composable
internal fun LockedPageContent(
    data: LockedPage,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = data.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = data.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }

        Box(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
        ) {
            data.buttons.forEach { button: Button ->
                ActionButton(
                    button = button,
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onAction = { action: ButtonAction ->
                        if (action is Back) {
                            onBack()
                        }
                    },
                )
            }
        }
    }
}
