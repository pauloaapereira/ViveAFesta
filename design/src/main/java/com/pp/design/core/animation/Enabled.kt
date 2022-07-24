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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import com.pp.design.utils.animateFloatByBoolean

const val EnabledAlpha = 1f
const val DisabledAlpha = .5f

fun Modifier.enabledAlpha(isEnabled: Boolean): Modifier = composed {
    val enabledTransition =
        updateTransition(targetState = isEnabled, label = "ComposifyEnabledAnimation")

    val enabledAlpha by enabledTransition.animateFloatByBoolean(
        onTrue = EnabledAlpha,
        onFalse = DisabledAlpha
    )

    alpha(enabledAlpha)
}
