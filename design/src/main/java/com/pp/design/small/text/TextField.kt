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
package com.pp.design.small.text

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.pp.design.R
import com.pp.design.background.LocalBaseContentColor
import com.pp.design.core.animation.enabledAlpha
import com.pp.design.small.icon.Icon

object TextField {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        label: String,
        isEnabled: Boolean = true,
        singleLine: Boolean = true,
        keyboardActions: KeyboardActions? = null,
        keyboardOptions: KeyboardOptions? = null,
        @DrawableRes leadingIcon: Int? = null,
        onFocusChanged: (Boolean) -> Unit,
        onValueChange: (String) -> Unit
    ) {
        val contentColor = LocalBaseContentColor.current
        val view = LocalView.current

        OutlinedTextField(
            modifier = modifier
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                }
                .fillMaxWidth()
                .enabledAlpha(isEnabled),
            value = text,
            onValueChange = { newQuery ->
                onValueChange(newQuery)
            },
            label = { Text(text = label) },
            leadingIcon = {
                Icon.Primary(
                    iconRes = leadingIcon,
                    contentDescription = stringResource(R.string.identification_text_field)
                )
            },
            trailingIcon = {
                Icon.Button(
                    iconRes = R.drawable.ic_clear,
                    contentDescription = stringResource(R.string.identification_text_field)
                ) {
                    if (text.isBlank()) {
                        view.clearFocus()
                    }
                    onValueChange("")
                }
            },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = singleLine,
            keyboardActions = keyboardActions ?: KeyboardActions.Default,
            keyboardOptions = keyboardOptions ?: KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect = false,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = contentColor,
                disabledTextColor = contentColor.copy(alpha = ContentAlpha.disabled),
                cursorColor = contentColor,
                errorCursorColor = MaterialTheme.colors.error,
                focusedBorderColor = contentColor,
                unfocusedBorderColor = contentColor,
                disabledBorderColor = contentColor.copy(alpha = ContentAlpha.disabled),
                errorBorderColor = MaterialTheme.colors.error,
                focusedLabelColor = contentColor,
                unfocusedLabelColor = contentColor,
                disabledLabelColor = contentColor,
                errorLabelColor = MaterialTheme.colors.error,
                placeholderColor = contentColor,
                disabledPlaceholderColor = contentColor.copy(ContentAlpha.disabled)
            ),
            enabled = isEnabled
        )
    }
}
