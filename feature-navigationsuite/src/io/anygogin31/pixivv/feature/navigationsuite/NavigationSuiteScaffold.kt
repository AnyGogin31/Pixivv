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

package io.anygogin31.pixivv.feature.navigationsuite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import io.anygogin31.pixivv.feature.navigationsuite.utils.WindowAdaptiveInfoDefault

private val NoWindowInsets: WindowInsets = WindowInsets(0, 0, 0, 0)

@Composable
public fun NavigationSuiteScaffold(
    navigationSuiteItems: NavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    navigationSuiteColors: NavigationSuiteColors = NavigationSuiteDefaults.colors(),
    containerColor: Color = NavigationSuiteScaffoldDefaults.containerColor,
    contentColor: Color = NavigationSuiteScaffoldDefaults.contentColor,
    content: @Composable () -> Unit,
) {
    val adaptiveInfo: WindowAdaptiveInfo = WindowAdaptiveInfoDefault

    val layoutType: NavigationSuiteType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)

    val contentPosition =
        when (adaptiveInfo.windowSizeClass.windowHeightSizeClass) {
            WindowHeightSizeClass.COMPACT -> NavigationContentPosition.TOP
            WindowHeightSizeClass.MEDIUM, WindowHeightSizeClass.EXPANDED -> NavigationContentPosition.CENTER

            else -> NavigationContentPosition.TOP
        }

    val contentType =
        when (adaptiveInfo.windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> NavigationContentType.SINGLE_PANE
            WindowWidthSizeClass.MEDIUM -> NavigationContentType.SINGLE_PANE
            WindowWidthSizeClass.EXPANDED -> NavigationContentType.DUAL_PANE

            else -> NavigationContentType.SINGLE_PANE
        }

    val contentScope: NavigationContentScope =
        NavigationContentScopeImpl(
            contentPosition = contentPosition,
            contentType = contentType,
            layoutType = layoutType,
            windowSizeClass = adaptiveInfo.windowSizeClass,
        )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = containerColor,
        contentColor = contentColor,
    ) {
        NavigationSuiteScaffoldLayout(
            navigationSuite = {
                NavigationSuite(
                    layoutType = layoutType,
                    colors = navigationSuiteColors,
                    content = navigationSuiteItems,
                )
            },
            layoutType = layoutType,
            content = {
                CompositionLocalProvider(
                    LocalNavigationContentScope provides contentScope,
                ) {
                    Box(
                        modifier =
                            Modifier.consumeWindowInsets(
                                when (layoutType) {
                                    NavigationSuiteType.NavigationBar ->
                                        NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Bottom)

                                    NavigationSuiteType.NavigationRail ->
                                        NavigationRailDefaults.windowInsets.only(WindowInsetsSides.Start)

                                    NavigationSuiteType.NavigationDrawer ->
                                        DrawerDefaults.windowInsets.only(WindowInsetsSides.Start)

                                    else -> NoWindowInsets
                                },
                            ),
                    ) {
                        content()
                    }
                }
            },
        )
    }
}
