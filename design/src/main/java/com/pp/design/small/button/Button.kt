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
package com.pp.design.small.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pp.design.R
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.clickableTrackingPress
import com.pp.design.core.animation.enabledAlpha
import com.pp.design.core.animation.pressedScale

object Button {

    @Composable
    fun Fab(
        modifier: Modifier = Modifier,
        text: String = "",
        @DrawableRes icon: Int? = null,
        enabled: Boolean = true,
        onClick: () -> Unit = {}
    ) {
        val contentColor = LocalBaseContentColor.current
        val shape = CircleShape

        ComposifyButton(
            modifier = modifier
                .clip(shape)
                .border(2.dp, contentColor, shape = shape),
            onClick = { onClick() },
            isEnabled = enabled,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = stringResource(R.string.button_leading_icon),
                        tint = contentColor
                    )
                }
                Text(text = text, color = contentColor, style = MaterialTheme.typography.button)
            }
        }
    }

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        @DrawableRes leadingIcon: Int? = null,
        @DrawableRes trailingIcon: Int? = null,
        enabled: Boolean = true,
        onClick: () -> Unit = {}
    ) {
        val contentColor = LocalBaseContentColor.current
        val shape = MaterialTheme.shapes.medium

        ComposifyButton(
            modifier = modifier
                .clip(shape)
                .border(2.dp, contentColor, shape = shape),
            onClick = { onClick() },
            isEnabled = enabled,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = stringResource(R.string.button_leading_icon),
                        tint = contentColor
                    )
                }
                Text(text = text, color = contentColor, style = MaterialTheme.typography.button)
                trailingIcon?.let {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = stringResource(R.string.button_trailing_icon),
                        tint = contentColor
                    )
                }
            }
        }
    }

    @Composable
    fun Text(
        modifier: Modifier = Modifier,
        text: String,
        shape: Shape = CircleShape,
        enabled: Boolean = true,
        onClick: () -> Unit = {}
    ) {
        val contentColor = LocalBaseContentColor.current

        ComposifyButton(
            modifier = modifier.clip(shape),
            onClick = { onClick() },
            isEnabled = enabled,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text, color = contentColor, style = MaterialTheme.typography.button)
            }
        }
    }

    @Composable
    private fun ComposifyButton(
        modifier: Modifier = Modifier,
        isEnabled: Boolean = true,
        onClick: () -> Unit,
        content: @Composable () -> Unit
    ) {
        var isPressing by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .enabledAlpha(isEnabled)
                .pressedScale(isPressing)
        ) {
            Box(
                modifier = modifier
                    .wrapContentSize()
                    .clickableTrackingPress(onPressChanged = { isPressing = it }) {
                        if (isEnabled) onClick()
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}
