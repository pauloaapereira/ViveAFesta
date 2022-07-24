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
package com.pp.design.medium.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pp.design.background.LocalBaseColor
import com.pp.design.background.LocalBaseContentColor

object Card {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        val backgroundColor = LocalBaseColor.current
        val contentColor = LocalBaseContentColor.current

        Card(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            border = BorderStroke(2.dp, contentColor),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(modifier = modifier.padding(16.dp)) {
                content()
            }
        }
    }
}
