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
package com.pp.design.large.tabs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.large.appbars.AppBar

object Tabs {

    data class TabRowItem(
        val title: String,
        @DrawableRes val iconRes: Int? = null,
        val isEnabled: Boolean = true
    )

    @Stable
    interface ITabRowState {
        var selectedTabIndex: Int
    }

    @Stable
    class TabRowState : ITabRowState {
        override var selectedTabIndex by mutableStateOf(0)
    }

    @Composable
    fun rememberTabRowState(): ITabRowState {
        return remember { TabRowState() }
    }

    @Composable
    fun TabRow(
        modifier: Modifier = Modifier,
        state: ITabRowState = rememberTabRowState(),
        items: List<TabRowItem>,
        onTabClick: (Int) -> Unit = {}
    ) {
        val contentColor = LocalBaseContentColor.current

        TabRow(
            modifier = modifier.fillMaxWidth(),
            selectedTabIndex = state.selectedTabIndex,
            contentColor = contentColor,
            backgroundColor = Color.Transparent
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = index == state.selectedTabIndex,
                    onClick = {
                        if (item.isEnabled) {
                            onTabClick(index)
                            state.selectedTabIndex = index
                        }
                    }
                ) {
                    AppBar.BarItem(
                        modifier = Modifier.padding(16.dp),
                        title = item.title,
                        iconRes = item.iconRes,
                        isEnabled = item.isEnabled
                    )
                }
            }
        }
    }
}
