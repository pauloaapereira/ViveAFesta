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
package com.pp.design.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pp.design.R

private val AppFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.comfortaa_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.comfortaa_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.comfortaa_semibold,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        )
    )
)

private val DefaultTypography = Typography()
val typography = Typography(
    h1 = DefaultTypography.h1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 93.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = DefaultTypography.h2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 58.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = DefaultTypography.h3.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp,
        letterSpacing = 0.sp
    ),
    h4 = DefaultTypography.h4.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 33.sp,
        letterSpacing = (0.25).sp
    ),
    h5 = DefaultTypography.h5.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
        letterSpacing = 0.sp
    ),
    h6 = DefaultTypography.h6.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        letterSpacing = (0.15).sp
    ),
    subtitle1 = DefaultTypography.subtitle1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = (0.15).sp
    ),
    subtitle2 = DefaultTypography.subtitle2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        letterSpacing = (0.1).sp
    ),
    body1 = DefaultTypography.body1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (0.5).sp
    ),
    body2 = DefaultTypography.body2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (0.25).sp
    ),
    button = DefaultTypography.button.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = (1.25).sp
    ),
    caption = DefaultTypography.caption.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (0.4).sp
    )
)
