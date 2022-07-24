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
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.pp.design.background.LocalBaseColor
import com.pp.design.utils.animateColorByBoolean

fun Modifier.selectedBackground(isSelected: Boolean): Modifier = composed {
    val selectedTransition =
        updateTransition(targetState = isSelected, label = "ComposifySelectedAnimation")

    val backgroundColor by selectedTransition.animateColorByBoolean(
        onTrue = LocalBaseColor.current,
        onFalse = Color.Transparent
    )

    background(backgroundColor)
}
