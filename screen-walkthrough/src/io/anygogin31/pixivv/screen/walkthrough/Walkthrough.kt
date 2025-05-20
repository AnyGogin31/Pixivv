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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.anygogin31.pixivv.feature.pager.HorizontalPager
import io.anygogin31.pixivv.feature.pager.PagerIndicator
import io.anygogin31.pixivv.feature.pager.PagerIndicatorIcon
import io.anygogin31.pixivv.feature.uri.UriLauncherProvider
import io.anygogin31.pixivv.screen.walkthrough.models.page.WalkthroughPage
import io.anygogin31.pixivv.screen.walkthrough.pages.AuthorizationPage
import io.anygogin31.pixivv.screen.walkthrough.pages.AuthorizationPageContent
import io.anygogin31.pixivv.screen.walkthrough.pages.ClientPolicyPage
import io.anygogin31.pixivv.screen.walkthrough.pages.ClientPolicyPageContent
import io.anygogin31.pixivv.screen.walkthrough.pages.LockedPage
import io.anygogin31.pixivv.screen.walkthrough.pages.LockedPageContent
import io.anygogin31.pixivv.screen.walkthrough.pages.ServicePolicyPage
import io.anygogin31.pixivv.screen.walkthrough.pages.ServicePolicyPageContent
import io.anygogin31.pixivv.screen.walkthrough.pages.WelcomePage
import io.anygogin31.pixivv.screen.walkthrough.pages.WelcomePageContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

// TODO: Refactor page unlocking logic for better clarity and efficiency
// TODO: Finalize background implementation using Pixiv illustrations for all pages

@Composable
public fun WalkthroughRoot(
    modifier: Modifier = Modifier,
    viewModel: WalkthroughViewModel = koinViewModel(),
) {
    val state: WalkthroughState by viewModel.state.collectAsStateWithLifecycle()

    WalkthroughScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun WalkthroughScreen(
    modifier: Modifier = Modifier,
    state: WalkthroughState,
    onAction: (action: WalkthroughAction) -> Unit,
) {
    UriLauncherProvider {
        val pageCount = fun(): Int = state.pages.size
        val pagerState: PagerState = rememberPagerState(pageCount = pageCount)

        val getPageByIndex = fun(index: Int): WalkthroughPage = state.pages[index]

        val coroutineScope: CoroutineScope = rememberCoroutineScope()

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                key = { index: Int ->
                    getPageByIndex(index).id.value
                },
            ) { index: Int ->
                val page: WalkthroughPage = getPageByIndex(index)
                val isUnlocked: Boolean = state.unlockedPages.contains(page)

                if (!isUnlocked) {
                    LockedPageContent(
                        data = LockedPage,
                        onBack = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                    )
                } else {
                    when (page) {
                        is WelcomePage -> WelcomePageContent(page)

                        is ServicePolicyPage -> {
                            ServicePolicyPageContent(
                                data = page,
                                onAgree = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                    onAction(WalkthroughAction.UnlockPage(getPageByIndex(index + 1)))
                                },
                            )
                        }

                        is ClientPolicyPage -> {
                            ClientPolicyPageContent(
                                data = page,
                                onAgree = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                    onAction(WalkthroughAction.UnlockPage(getPageByIndex(index + 1)))
                                },
                            )
                        }

                        is AuthorizationPage -> AuthorizationPageContent(page)
                    }
                }
            }

            AnimatedVisibility(
                visible =
                    !(
                        state.unlockedPages.contains(state.pages.lastOrNull()) &&
                            pagerState.currentPage == state.pages.size - 1
                    ) &&
                        state.pages.isNotEmpty(),
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                PagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.padding(16.dp),
                ) { index: Int ->
                    val page: WalkthroughPage = getPageByIndex(index)
                    val isUnlocked: Boolean = state.unlockedPages.contains(page)
                    val isCurrentPage: Boolean = pagerState.currentPage == index
                    PagerIndicatorIcon(
                        color =
                            if (isCurrentPage) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                        icon =
                            when {
                                !isUnlocked -> Icons.Default.Lock
                                page is AuthorizationPage && isUnlocked -> Icons.Default.Person
                                else -> null
                            },
                    )
                }
            }
        }
    }
}
