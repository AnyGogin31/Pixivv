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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import io.anygogin31.pixivv.domain.models.IllustModel
import io.anygogin31.pixivv.feature.pager.HorizontalPager
import io.anygogin31.pixivv.feature.pager.PagerIndicator
import io.anygogin31.pixivv.feature.pager.PagerIndicatorIcon
import io.anygogin31.pixivv.feature.uri.UriLauncherProvider
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageAction
import io.anygogin31.pixivv.screen.walkthrough.pages.WalkthroughPageNode
import io.anygogin31.pixivv.screen.walkthrough.pages.auth.AuthorizationPage
import io.anygogin31.pixivv.screen.walkthrough.pages.locked.LockedPageContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
public fun WalkthroughRoot(
    modifier: Modifier = Modifier,
    viewModel: WalkthroughViewModel = koinViewModel(),
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
    UriLauncherProvider {
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            WalkthroughBackground(
                walkthroughIllust = state.walkthroughIllust,
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val pages: List<WalkthroughPageNode> = generateSequence(state.pages) { it.next }.toList()
                val pageCount: Int = pages.size
                val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })

                val getPageByIndex = fun(index: Int): WalkthroughPageNode = pages[index]

                val coroutineScope: CoroutineScope = rememberCoroutineScope()

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f),
                    key = { index: Int -> getPageByIndex(index).id.value },
                ) { index: Int ->
                    val page: WalkthroughPageNode = getPageByIndex(index)
                    if (page.isUnlocked) {
                        page.Content(
                            onAction = { action: WalkthroughPageAction ->
                                when (action) {
                                    WalkthroughPageAction.NextPage -> {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index + 1)
                                        }
                                    }
                                }
                            },
                        )
                    } else {
                        LockedPageContent(
                            onBack = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index - 1)
                                }
                            },
                        )
                    }
                }

                val isLastPage: Boolean = pagerState.currentPage == pageCount - 1
                val isLastPageUnlocked: Boolean = pages.last().isUnlocked
                AnimatedVisibility(
                    visible = !(isLastPage && isLastPageUnlocked),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                ) {
                    PagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.padding(16.dp),
                    ) { index: Int ->
                        val page: WalkthroughPageNode = getPageByIndex(index)
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
                                    !page.isUnlocked -> Icons.Default.Lock
                                    page is AuthorizationPage && page.isUnlocked -> Icons.Default.Person
                                    else -> null
                                },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WalkthroughBackground(
    walkthroughIllust: List<IllustModel>,
    modifier: Modifier = Modifier,
) {
    if (walkthroughIllust.isEmpty()) {
        return
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        val gridState: LazyGridState = rememberLazyGridState()

        LaunchedEffect(key1 = Unit) {
            while (true) {
                gridState.animateScrollBy(
                    value = 0.45f,
                    animationSpec =
                        tween(
                            durationMillis = 16,
                            easing = LinearEasing,
                        ),
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            state = gridState,
            userScrollEnabled = false,
        ) {
            items(count = Int.MAX_VALUE) { index: Int ->
                val item: IllustModel = walkthroughIllust[index % walkthroughIllust.size]
                AsyncImage(
                    model = item.imageUrls,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .aspectRatio(1f),
                )
            }
        }

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)),
        )
    }
}
