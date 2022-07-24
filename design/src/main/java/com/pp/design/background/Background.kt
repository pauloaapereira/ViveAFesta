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
package com.pp.design.background

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pp.design.core.animation.MinAnimationDuration
import com.pp.design.utils.generateGradientFeel
import com.pp.design.utils.getContentColor

@Stable
interface ColorState {
    var baseColor: Color
}

@Stable
class BackgroundState(baseColor: Color) : ColorState {
    override var baseColor by mutableStateOf(baseColor)
    val contentColor: Color
        get() = baseColor.getContentColor()
}

@Composable
fun rememberBackgroundState(
    startBaseColor: Color = MaterialTheme.colors.primary,
): BackgroundState {
    return remember { BackgroundState(startBaseColor) }
}

@Composable
fun Background(
    state: BackgroundState = rememberBackgroundState(),
    content: @Composable () -> Unit
) {
    val backgroundColorFeel by animateColorAsState(
        targetValue = state.baseColor,
        animationSpec = tween(MinAnimationDuration)
    )

    CompositionLocalProvider(
        LocalBaseColor provides state.baseColor,
        LocalBaseContentColor provides state.contentColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(generateGradientFeel(backgroundColorFeel))
        ) {
            content()
        }
    }
}

val LocalBaseColor: ProvidableCompositionLocal<Color> = staticCompositionLocalOf {
    error("LocalBaseColor is being used without being provided by a Background composable.")
}

val LocalBaseContentColor: ProvidableCompositionLocal<Color> = staticCompositionLocalOf {
    error("LocalBaseContentColor is being used without being provided by a Background composable.")
}
