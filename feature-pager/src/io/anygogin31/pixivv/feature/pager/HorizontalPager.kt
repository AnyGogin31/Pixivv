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

package io.anygogin31.pixivv.feature.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import io.anygogin31.pixivv.feature.pager.modifiers.desktopPagerScrollFix
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.ranges.coerceAtMost

@ExperimentalFoundationApi
@Composable
public fun HorizontalPager(
    state: PagerState,
    modifier: Modifier = Modifier,
    key: ((index: Int) -> Any)? = null,
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val animateScrollToPage = fun(page: () -> Int) {
        coroutineScope.launch {
            state.animateScrollToPage(page())
        }
    }

    HorizontalPager(
        state = state,
        modifier =
            modifier.desktopPagerScrollFix(
                onNext = {
                    coroutineScope.launch {
                        animateScrollToPage {
                            (state.currentPage + 1).coerceAtMost(state.pageCount - 1)
                        }
                    }
                },
                onPrevious = {
                    coroutineScope.launch {
                        animateScrollToPage {
                            (state.currentPage - 1).coerceAtLeast(0)
                        }
                    }
                },
            ),
        key = key,
        pageContent = pageContent,
    )
}
