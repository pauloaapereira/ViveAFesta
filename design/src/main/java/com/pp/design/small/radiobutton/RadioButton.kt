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
package com.pp.design.small.radiobutton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pp.design.background.LocalBaseColor
import com.pp.design.core.animation.enabledAlpha
import com.pp.design.small.text.Text

object RadioButton {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        isChecked: Boolean = false,
        isEnabled: Boolean = true,
        onSelect: (String) -> Unit
    ) {
        val backgroundColor = LocalBaseColor.current
        var isSelected by remember { mutableStateOf(false) }

        LaunchedEffect(isChecked) {
            isSelected = isChecked
        }

        fun select() {
            if (isEnabled && !isSelected) {
                isSelected = true
                onSelect(text)
            }
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
            RadioButton(
                selected = isSelected,
                onClick = {
                    select()
                },
                enabled = isEnabled,
                colors = RadioButtonDefaults.colors(
                    selectedColor = backgroundColor,
                    unselectedColor = backgroundColor,
                )
            )
            Text.Button(text = text)
        }
    }

    @Composable
    fun Group(
        modifier: Modifier = Modifier,
        orientation: Orientation = Orientation.Vertical,
        options: List<String>,
        onItemSelected: (String) -> Unit
    ) {
        var selectedItem by remember { mutableStateOf(0) }

        when (orientation) {
            Orientation.Vertical -> {
                LazyColumn(modifier) {
                    itemsIndexed(options) { index, option ->
                        GroupItem(isSelected = index == selectedItem, text = option) {
                            selectedItem = index
                            onItemSelected(option)
                        }
                    }
                }
            }
            Orientation.Horizontal -> {
                LazyRow(modifier) {
                    itemsIndexed(options) { index, option ->
                        GroupItem(isSelected = index == selectedItem, text = option) {
                            selectedItem = index
                            onItemSelected(option)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun GroupItem(
        modifier: Modifier = Modifier,
        isSelected: Boolean,
        text: String,
        onSelect: () -> Unit
    ) {
        Primary(
            modifier = modifier,
            text = text,
            isChecked = isSelected,
            onSelect = {
                onSelect()
            }
        )
    }
}
