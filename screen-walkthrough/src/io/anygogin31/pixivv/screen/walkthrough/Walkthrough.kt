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

package io.anygogin31.pixivv.screen.walkthrough

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.anygogin31.pixivv.feature.pager.HorizontalPager
import io.anygogin31.pixivv.feature.pager.PagerIndicator
import io.anygogin31.pixivv.feature.pager.PagerIndicatorIcon
import io.anygogin31.pixivv.screen.walkthrough.models.button.Agree
import io.anygogin31.pixivv.screen.walkthrough.models.button.Button
import io.anygogin31.pixivv.screen.walkthrough.models.button.ButtonAction
import io.anygogin31.pixivv.screen.walkthrough.models.button.OpenBrowser
import io.anygogin31.pixivv.screen.walkthrough.models.page.AuthorizationPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.ClientPolicyPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.ServicePolicyPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPage
import io.anygogin31.pixivv.screen.walkthrough.models.page.WelcomePage

@Composable
public fun WalkthroughRoot(
    modifier: Modifier = Modifier,
    viewModel: WalkthroughViewModel = viewModel(),
) {
    val state: WalkthroughState by viewModel.state.collectAsStateWithLifecycle()

    WalkthroughScreen(
        modifier = modifier,
        state = state,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun WalkthroughScreen(
    modifier: Modifier = Modifier,
    state: WalkthroughState,
) {
    val pageCount: () -> Int = { state.pages.size }
    val pagerState: PagerState = rememberPagerState(pageCount = pageCount)

    val getPageByIndex = fun(index: Int): WalkthroughPage = state.pages[index]

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            key = { index: Int ->
                getPageByIndex(index)
            },
        ) { index: Int ->
            val page: WalkthroughPage = getPageByIndex(index)
            when (page) {
                is WelcomePage -> WelcomePageContent(page)
                is ServicePolicyPage -> ServicePolicyPageContent(page)
                is ClientPolicyPage -> ClientPolicyPageContent(page)
                is AuthorizationPage -> AuthorizationPageContent(page)
            }
        }

        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp),
        ) { page: Int ->
            PagerIndicatorIcon()
        }
    }
}

@Composable
private fun WelcomePageContent(data: WelcomePage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
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
}

@Composable
private fun ServicePolicyPageContent(data: ServicePolicyPage) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
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

        Row(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            data.buttons.forEach { button: Button ->
                ActionButton(
                    button = button,
                    modifier = Modifier.weight(1f),
                    onAction = { action: ButtonAction ->
                        when (action) {
                            is OpenBrowser -> TODO()
                            is Agree -> TODO()
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun ClientPolicyPageContent(data: ClientPolicyPage) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
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

        Row(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            data.buttons.forEach { button: Button ->
                ActionButton(
                    button = button,
                    modifier = Modifier.weight(1f),
                    onAction = { action: ButtonAction ->
                        when (action) {
                            is OpenBrowser -> TODO()
                            is Agree -> TODO()
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun AuthorizationPageContent(data: AuthorizationPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
                        if (action is OpenBrowser) TODO()
                    },
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    button: Button,
    modifier: Modifier = Modifier,
    onAction: (action: ButtonAction) -> Unit = {},
) {
    Button(
        onClick = {
            onAction(button.action)
        },
        modifier = modifier,
    ) {
        Text(text = button.text)
    }
}
