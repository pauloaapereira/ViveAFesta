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
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import com.pp.design.utils.animateFloatByBoolean
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PressedScale = 1.1f
const val UnpressedScale = 1f

fun Modifier.pressedScale(isPressed: Boolean): Modifier = composed {
    val pressedTransition =
        updateTransition(targetState = isPressed, label = "ComposifyPressedAnimation")

    val pressedScale by pressedTransition.animateFloatByBoolean(
        onTrue = PressedScale,
        onFalse = UnpressedScale
    )

    scale(pressedScale)
}

fun Modifier.clickableTrackingPress(
    onPressChanged: (Boolean) -> Unit,
    onClick: () -> Unit
): Modifier = composed {
    val coroutineScope = rememberCoroutineScope()

    clickable {
        onClick()
        coroutineScope.launch {
            onPressChanged(true)
            delay(MinAnimationDuration / 2L)
            onPressChanged(false)
        }
    }
}
