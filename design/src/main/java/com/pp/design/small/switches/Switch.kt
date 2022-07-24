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
package com.pp.design.small.switches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pp.design.background.LocalBaseColor
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.enabledAlpha
import com.pp.design.small.text.Text

object Switch {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        isEnabled: Boolean = true,
        onCheckedChange: (Boolean) -> Unit
    ) {
        val backgroundColor = LocalBaseColor.current
        val contentColor = LocalBaseContentColor.current
        var isChecked by remember { mutableStateOf(false) }

        fun check(checked: Boolean) {
            if (!isEnabled) return
            isChecked = checked
            onCheckedChange(checked)
        }

        Row(
            modifier = modifier
                .clip(CircleShape)
                .clickable { check(!isChecked) }
                .padding(8.dp)
                .enabledAlpha(isEnabled),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text.HeaderSix(text = text)
            Switch(
                modifier = Modifier.padding(top = 6.dp),
                enabled = isEnabled,
                checked = isChecked,
                onCheckedChange = { checked ->
                    check(checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = contentColor,
                    uncheckedThumbColor = backgroundColor,
                    uncheckedTrackColor = contentColor
                )
            )
        }
    }
}
