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
package com.pp.design.medium.snackbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import com.pp.design.background.LocalBaseColor
import com.pp.design.background.LocalBaseContentColor

object Snackbar {

    @Composable
    fun Primary(snackBarData: SnackbarData) {
        val backgroundColor = LocalBaseColor.current
        val contentColor = LocalBaseContentColor.current

        Snackbar(
            snackbarData = snackBarData,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            actionColor = contentColor,
            shape = MaterialTheme.shapes.medium,
            actionOnNewLine = snackBarData.message.length > 35
        )
    }
}
