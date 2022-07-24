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
package com.pp.design.small.chip

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pp.design.R
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.clickableTrackingPress
import com.pp.design.core.animation.pressedScale
import com.pp.design.core.animation.selectedBackground
import com.pp.design.small.icon.Icon

object Chip {

    enum class IconPosition {
        START,
        END
    }

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        @DrawableRes selectedIcon: Int? = null,
        iconPosition: IconPosition? = null,
        onSelected: (String) -> Unit = {}
    ) {
        var isSelected by remember { mutableStateOf(false) }
        var isPressing by remember { mutableStateOf(false) }

        val contentColor = LocalBaseContentColor.current

        Box(modifier = modifier.pressedScale(isPressing)) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
                    .border(2.dp, contentColor, shape = CircleShape)
                    .selectedBackground(isSelected)
                    .clickableTrackingPress(onPressChanged = { isPressing = it }) {
                        isSelected = !isSelected
                        onSelected(text)
                    }
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SelectedIcon(
                        isSelected = isSelected,
                        isVisible = iconPosition == IconPosition.START,
                        selectedIcon = selectedIcon
                    )
                    Text(text = text, color = contentColor, style = MaterialTheme.typography.button)
                    SelectedIcon(
                        isSelected = isSelected,
                        isVisible = iconPosition == IconPosition.END,
                        selectedIcon = selectedIcon
                    )
                }
            }
        }
    }

    @Composable
    private fun SelectedIcon(
        isVisible: Boolean,
        isSelected: Boolean,
        selectedIcon: Int?
    ) {
        if (isVisible) {
            Icon.Primary(
                iconRes = selectedIcon,
                contentDescription = stringResource(R.string.button_leading_icon),
                iconSize = 24.dp,
                isVisible = isSelected
            )
        }
    }
}
