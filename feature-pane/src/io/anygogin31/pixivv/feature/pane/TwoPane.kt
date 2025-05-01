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

package io.anygogin31.pixivv.feature.pane

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrain
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
public fun TwoPane(
    firstContent: @Composable () -> Unit,
    secondContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    splitFraction: Float = 0.5f,
    dividerWidth: Dp = 16.dp,
) {
    val slotIdFirst: TwoPaneSlot = remember { TwoPaneSlot.FIRST }
    val slotIdSecond: TwoPaneSlot = remember { TwoPaneSlot.SECOND }

    val animatedSplitFraction: Float by animateFloatAsState(
        targetValue = splitFraction,
        animationSpec = tween(durationMillis = 300),
        label = "splitFractionAnimation",
    )

    Layout(
        content = {
            Box(
                modifier = Modifier.layoutId(slotIdFirst),
                content = {
                    firstContent()
                },
            )
            Box(
                modifier = Modifier.layoutId(slotIdSecond),
                content = {
                    secondContent()
                },
            )
        },
        modifier = modifier,
    ) { measurables: List<Measurable>, constraints: Constraints ->
        val placeables: Map<Any?, Measurable> = measurables.associateBy { it.layoutId }
        val firstMeasurable: Measurable = placeables[slotIdFirst]!!
        val secondMeasurable: Measurable = placeables[slotIdSecond]!!

        layout(constraints.maxWidth, constraints.maxHeight) {
            val layoutCoordinates: LayoutCoordinates =
                checkNotNull(coordinates) {
                    "TwoPane does not support the use of alignment lines!"
                }

            val splitX: Float =
                layoutCoordinates.size.width *
                    when (layoutDirection) {
                        LayoutDirection.Ltr -> animatedSplitFraction
                        LayoutDirection.Rtl -> 1 - animatedSplitFraction
                    }
            val dividerWidthPixel: Float = dividerWidth.toPx()

            val bounds =
                Rect(
                    left = splitX - dividerWidthPixel / 2f,
                    top = 0f,
                    right = splitX + dividerWidthPixel / 2f,
                    bottom = layoutCoordinates.size.height.toFloat(),
                )

            val leftBounds: Int = constraints.constrainWidth(bounds.left.roundToInt())
            val rightBounds: Int = constraints.constrainWidth(bounds.right.roundToInt())

            val firstConstraints: Constraints =
                constraints.copy(
                    minWidth = leftBounds,
                    maxWidth = leftBounds,
                )
            val secondConstraints: Constraints =
                constraints.copy(
                    minWidth = constraints.maxWidth - rightBounds,
                    maxWidth = constraints.maxWidth - rightBounds,
                )

            val firstPlaceable: Placeable = firstMeasurable.measure(constraints.constrain(firstConstraints))
            val secondPlaceable: Placeable = secondMeasurable.measure(constraints.constrain(secondConstraints))

            firstPlaceable.placeRelative(IntOffset.Zero)
            secondPlaceable.placeRelative(x = constraints.maxWidth - secondPlaceable.width, y = 0)
        }
    }
}

private enum class TwoPaneSlot {
    FIRST,
    SECOND,
}
