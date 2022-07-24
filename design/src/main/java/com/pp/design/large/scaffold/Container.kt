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
package com.pp.design.large.scaffold

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.pp.design.background.LocalBaseColor
import com.pp.design.background.LocalBaseContentColor

object Container {

    @Composable
    fun ScaffoldContainer(
        modifier: Modifier = Modifier,
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        topBar: @Composable () -> Unit = {},
        bottomBar: @Composable () -> Unit = {},
        snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
        floatingActionButton: @Composable () -> Unit = {},
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        isFloatingActionButtonDocked: Boolean = false,
        drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
        drawerGesturesEnabled: Boolean = true,
        drawerShape: Shape = MaterialTheme.shapes.large,
        drawerElevation: Dp = DrawerDefaults.Elevation,
        drawerBackgroundColor: Color = MaterialTheme.colors.surface,
        drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
        drawerScrimColor: Color = DrawerDefaults.scrimColor,
        content: @Composable (PaddingValues) -> Unit
    ) {
        Scaffold(
            modifier,
            scaffoldState,
            topBar,
            bottomBar,
            snackbarHost,
            floatingActionButton,
            floatingActionButtonPosition,
            isFloatingActionButtonDocked,
            drawerContent,
            drawerGesturesEnabled,
            drawerShape,
            drawerElevation,
            drawerBackgroundColor,
            drawerContentColor,
            drawerScrimColor,
            LocalBaseColor.current,
            LocalBaseContentColor.current,
            content
        )
    }

    @ExperimentalMaterialApi
    @Composable
    fun BottomSheet(
        sheetContent: @Composable ColumnScope.() -> Unit,
        modifier: Modifier = Modifier,
        scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        topBar: (@Composable () -> Unit)? = null,
        snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
        floatingActionButton: (@Composable () -> Unit)? = null,
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        sheetGesturesEnabled: Boolean = true,
        sheetShape: Shape = MaterialTheme.shapes.large,
        sheetElevation: Dp = BottomSheetScaffoldDefaults.SheetElevation,
        sheetPeekHeight: Dp = BottomSheetScaffoldDefaults.SheetPeekHeight,
        drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
        drawerGesturesEnabled: Boolean = true,
        drawerShape: Shape = MaterialTheme.shapes.large,
        drawerElevation: Dp = DrawerDefaults.Elevation,
        drawerBackgroundColor: Color = MaterialTheme.colors.surface,
        drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
        drawerScrimColor: Color = DrawerDefaults.scrimColor,
        content: @Composable (PaddingValues) -> Unit
    ) {
        BottomSheetScaffold(
            sheetContent,
            modifier,
            scaffoldState,
            topBar,
            snackbarHost,
            floatingActionButton,
            floatingActionButtonPosition,
            sheetGesturesEnabled,
            sheetShape,
            sheetElevation,
            LocalBaseColor.current,
            LocalBaseContentColor.current,
            sheetPeekHeight,
            drawerContent,
            drawerGesturesEnabled,
            drawerShape,
            drawerElevation,
            drawerBackgroundColor,
            drawerContentColor,
            drawerScrimColor,
            Color.Transparent,
            LocalBaseContentColor.current,
            content
        )
    }
}
