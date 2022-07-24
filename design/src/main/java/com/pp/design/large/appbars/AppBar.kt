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
package com.pp.design.large.appbars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pp.design.background.LocalBaseColor
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.clickableTrackingPress
import com.pp.design.core.animation.enabledAlpha
import com.pp.design.core.animation.pressedScale
import com.pp.design.small.icon.Icon
import com.pp.design.small.text.Text

object AppBar {

    data class BottomBarItem(
        val title: String,
        @DrawableRes val iconRes: Int?,
        val isEnabled: Boolean = true,
        val onClick: (() -> Unit)? = null
    )

    @Composable
    fun Top(
        modifier: Modifier = Modifier,
        shape: Shape? = null,
        isScrollable: Boolean = false,
        content: @Composable RowScope.() -> Unit
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            color = LocalBaseColor.current,
            contentColor = LocalBaseContentColor.current,
            shape = shape ?: RectangleShape
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .apply {
                        if (isScrollable)
                            horizontalScroll(rememberScrollState())
                        else
                            Modifier
                    },
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    }

    @Composable
    fun Bottom(
        modifier: Modifier = Modifier,
        items: List<BottomBarItem>,
        shape: Shape? = null,
        isScrollable: Boolean = false
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            color = LocalBaseColor.current,
            contentColor = LocalBaseContentColor.current,
            shape = shape ?: RectangleShape
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth().apply {
                        if (isScrollable)
                            horizontalScroll(rememberScrollState())
                        else
                            Modifier
                    },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    BarItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        title = item.title,
                        iconRes = item.iconRes,
                        onClick = item.onClick,
                        shape = shape,
                        isEnabled = item.isEnabled
                    )
                }
            }
        }
    }

    @Composable
    fun BarItem(
        modifier: Modifier = Modifier,
        isEnabled: Boolean = true,
        title: String,
        @DrawableRes iconRes: Int? = null,
        shape: Shape? = null,
        onClick: (() -> Unit)? = null
    ) {
        var isPressed by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .enabledAlpha(isEnabled)
                .then(
                    if (onClick != null) {
                        Modifier
                            .pressedScale(isPressed)
                            .then(
                                if (shape != null) {
                                    Modifier.clip(shape)
                                } else Modifier
                            )
                            .clickableTrackingPress(
                                onPressChanged = {
                                    isPressed = it
                                }
                            ) {
                                if (isEnabled)
                                    onClick()
                            }
                    } else Modifier
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon.Primary(
                iconRes = iconRes,
                contentDescription = title,
                isVisible = iconRes != null
            )
            Text.Button(
                text = title,
                parameters = Text.TextParameters(textAlign = TextAlign.Center)
            )
        }
    }
}
