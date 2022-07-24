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
package com.pp.design.medium.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pp.design.medium.card.Card

object Dialog {

    @Composable
    fun Primary(
        isVisible: Boolean = true,
        onDismiss: () -> Unit = {},
        dismissOnBackPress: Boolean = true,
        dismissOnClickOutside: Boolean = true,
        content: @Composable () -> Unit
    ) {
        if (isVisible) {
            Dialog(
                properties = DialogProperties(
                    dismissOnBackPress = dismissOnBackPress,
                    dismissOnClickOutside = dismissOnClickOutside
                ),
                onDismissRequest = onDismiss
            ) {
                Card.Primary {
                    content()
                }
            }
        }
    }
}
