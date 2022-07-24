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
package com.pp.design.medium.slider

import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.enabledAlpha

object Slider {

    @Composable
    fun Primary(
        isEnabled: Boolean = true
    ) {
        val contentColor = LocalBaseContentColor.current
        var value by remember { mutableStateOf(0f) }

        Slider(
            modifier = Modifier.enabledAlpha(isEnabled),
            value = value,
            onValueChange = { value = it },
            enabled = isEnabled,
            colors = SliderDefaults.colors(
                thumbColor = contentColor,
                activeTrackColor = contentColor
            )
        )
    }
}
