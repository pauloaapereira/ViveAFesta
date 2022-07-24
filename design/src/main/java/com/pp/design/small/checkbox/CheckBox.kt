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
package com.pp.design.small.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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

object CheckBox {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        isEnabled: Boolean = true,
        onCheckedChange: (String, Boolean) -> Unit
    ) {
        val backgroundColor = LocalBaseColor.current
        val contentColor = LocalBaseContentColor.current

        var isSelected by remember { mutableStateOf(false) }

        fun select() {
            if (!isEnabled) return
            isSelected = !isSelected
            onCheckedChange(text, isSelected)
        }

        Row(
            modifier = modifier
                .clip(CircleShape)
                .clickable { select() }
                .padding(8.dp)
                .enabledAlpha(isEnabled),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                enabled = isEnabled,
                onCheckedChange = { select() },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = contentColor,
                    checkedColor = backgroundColor,
                    uncheckedColor = backgroundColor
                )
            )
            Text.Button(text = text)
        }
    }
}
