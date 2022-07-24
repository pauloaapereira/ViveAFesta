/*
 * Copyright 2021 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pp.design.core.animation

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pp.design.utils.animateDpByBoolean
import com.pp.design.utils.animateFloatByBoolean

const val VisibleAlpha = 1f
const val InvisibleAlpha = 0f

const val VisibleRotation = 0f
const val InvisibleRotation = -180f

val InvisibleSize = 0.dp

fun Modifier.toggleVisibility(isVisible: Boolean, visibleSize: Dp): Modifier = composed {
    val visibilityTransition =
        updateTransition(targetState = isVisible, label = "ComposifyIconVisibilityAnimation")

    val visibilityAlpha by visibilityTransition.animateFloatByBoolean(
        onTrue = VisibleAlpha,
        onFalse = InvisibleAlpha
    )

    val visibilityRotation by visibilityTransition.animateFloatByBoolean(
        onTrue = VisibleRotation,
        onFalse = InvisibleRotation
    )

    val visibilitySize by visibilityTransition.animateDpByBoolean(
        onTrue = visibleSize,
        onFalse = InvisibleSize
    )

    requiredSize(visibilitySize)
        .alpha(visibilityAlpha)
        .graphicsLayer {
            rotationZ = visibilityRotation
        }
}
