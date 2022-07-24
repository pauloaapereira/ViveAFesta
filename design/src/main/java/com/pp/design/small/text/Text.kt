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

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.pp.design.background.LocalBaseContentColor

object Text {

    @Composable
    fun HeaderOne(
        text: String,
        modifier: Modifier = Modifier,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h1,
            parameters = parameters
        )
    }

    @Composable
    fun HeaderTwo(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h2,
            parameters = parameters
        )
    }

    @Composable
    fun HeaderThree(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h3,
            parameters = parameters
        )
    }

    @Composable
    fun HeaderFour(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h4,
            parameters = parameters
        )
    }

    @Composable
    fun HeaderFive(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h5,
            parameters = parameters
        )
    }

    @Composable
    fun HeaderSix(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.h6,
            parameters = parameters
        )
    }

    @Composable
    fun SubtitleOne(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.subtitle1,
            parameters = parameters
        )
    }

    @Composable
    fun SubtitleTwo(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.subtitle2,
            parameters = parameters
        )
    }

    @Composable
    fun BodyOne(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.body1,
            parameters = parameters
        )
    }

    @Composable
    fun BodyTwo(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.body2,
            parameters = parameters
        )
    }

    @Composable
    fun Button(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.button,
            parameters = parameters
        )
    }

    @Composable
    fun Caption(
        modifier: Modifier = Modifier,
        text: String,
        parameters: TextParameters? = null
    ) {
        ComposifyText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.caption,
            parameters = parameters
        )
    }

    data class TextParameters(
        val textDecoration: TextDecoration? = null,
        val textAlign: TextAlign? = null,
        val overflow: TextOverflow = TextOverflow.Clip,
        val softWrap: Boolean = true,
        val maxLines: Int = Int.MAX_VALUE,
        val onTextLayout: (TextLayoutResult) -> Unit = {}
    )

    @Composable
    private fun ComposifyText(
        modifier: Modifier = Modifier,
        text: String,
        style: TextStyle,
        parameters: TextParameters? = null
    ) {
        val contentColor = LocalBaseContentColor.current
        val textParameters = parameters ?: TextParameters(
            textDecoration = null,
            textAlign = null,
            overflow = TextOverflow.Clip,
            softWrap = true,
            maxLines = Int.MAX_VALUE,
            onTextLayout = {}
        )

        Text(
            text = text,
            modifier = modifier,
            textDecoration = textParameters.textDecoration,
            textAlign = textParameters.textAlign,
            overflow = textParameters.overflow,
            softWrap = textParameters.softWrap,
            maxLines = textParameters.maxLines,
            onTextLayout = textParameters.onTextLayout,
            color = contentColor,
            style = style
        )
    }
}
