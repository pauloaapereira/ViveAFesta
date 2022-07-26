/*
 * Copyright 2022 Paulo Pereira
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
package com.pp.viveafesta.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.pp.design.background.LocalBaseColor
import com.pp.design.large.appbars.AppBar
import com.pp.design.small.button.Button
import com.pp.design.small.icon.Icon
import com.pp.design.small.text.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private enum class Page {
    Add,
    List,
    Filter
}

@OptIn(ExperimentalPagerApi::class)
private fun PagerState.moveToPage(page: Page, coroutineScope: CoroutineScope) {
    coroutineScope.launch { animateScrollToPage(page.ordinal) }
}

@ExperimentalPagerApi
@Composable
fun PartyScreen(viewModel: MainViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = Page.List.ordinal)

    LaunchedEffect(Unit, viewModel.filters) {
        viewModel.fetchParties()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                onFilterClicked = {
                    pagerState.moveToPage(Page.Filter, coroutineScope)
                }
            )

            Divider(Modifier.fillMaxWidth())

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                count = Page.values().size
            ) { page ->
                when (page) {
                    Page.Add.ordinal -> {
                        AddParty(
                            viewModel = viewModel,
                            onAddClicked = { pagerState.moveToPage(Page.List, coroutineScope) }
                        )
                    }
                    Page.List.ordinal -> {
                        PartyList(
                            viewModel = viewModel,
                            contentPadding = PaddingValues(top = 16.dp, bottom = 72.dp)
                        )
                    }
                    Page.Filter.ordinal -> {
                        FilterParty(viewModel = viewModel)
                    }
                }
            }
        }

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(start = 16.dp)) {
            AnimatedVisibility(
                visible = pagerState.currentPage != Page.Add.ordinal,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AddPartyButton(onClick = { pagerState.moveToPage(Page.Add, coroutineScope) })
            }
        }
    }
}

@Composable
private fun Header(onFilterClicked: () -> Unit) {
    AppBar.Top(horizontalArrangement = Arrangement.SpaceBetween) {
        Text.HeaderFive(text = "Vive a Festa")
        Icon.Button(iconRes = R.drawable.ic_filter, onClick = onFilterClicked)
    }
}


@Composable
private fun AddPartyButton(onClick: () -> Unit) {
    Button.Fab(
        outerModifier = Modifier.padding(16.dp),
        text = "Adicionar festa",
        backgroundColor = LocalBaseColor.current,
        onClick = onClick
    )
}
