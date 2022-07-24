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
package com.pp.design.utils

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.pp.design.core.animation.MinAnimationDuration

@Composable
fun Transition<Boolean>.animateFloatByBoolean(duration: Int = MinAnimationDuration / 2, onTrue: Float, onFalse: Float, label: String = ""): State<Float> {
    return animateFloat(
        transitionSpec = { tween(durationMillis = duration) },
        label = label
    ) { state ->
        when (state) {
            true -> onTrue
            false -> onFalse
        }
    }
}
@Composable
fun Transition<Boolean>.animateColorByBoolean(duration: Int = MinAnimationDuration / 2, onTrue: Color, onFalse: Color, label: String = ""): State<Color> {
    return animateColor(
        transitionSpec = { tween(durationMillis = duration) },
        label = label
    ) { state ->
        when (state) {
            true -> onTrue
            false -> onFalse
        }
    }
}

@Composable
fun Transition<Boolean>.animateDpByBoolean(duration: Int = MinAnimationDuration / 2, onTrue: Dp, onFalse: Dp, label: String = ""): State<Dp> {
    return animateDp(
        transitionSpec = { tween(durationMillis = duration) },
        label = label
    ) { state ->
        when (state) {
            true -> onTrue
            false -> onFalse
        }
    }
}
