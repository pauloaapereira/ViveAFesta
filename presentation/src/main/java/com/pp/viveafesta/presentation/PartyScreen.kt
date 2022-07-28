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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pp.design.small.text.Text
import com.pp.viveafesta.presentation.models.ViewParty

const val PartyListScreen = "PartyListScreen"

@Composable
fun PartyScreen() {
    val viewModel = hiltViewModel<MainViewModel>()

    LaunchedEffect(Unit, viewModel.filters) {
        viewModel.fetchParties()
    }

    LazyColumn(contentPadding = PaddingValues(top = 16.dp, bottom = 72.dp)) {
        items(viewModel.parties) { party ->
            Party(party)
            Spacer(modifier = Modifier.size(16.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}


@Composable
private fun Party(party: ViewParty) {
    val configuration = LocalConfiguration.current

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        party.poster?.let {
            AsyncImage(
                modifier = Modifier
                    .requiredWidth(configuration.screenWidthDp.dp * .8f)
                    .requiredHeightIn(max = 400.dp),
                model = it, contentDescription = "cartaz"
            )
        }
        Text.HeaderSix(
            text = party.name,
            parameters = Text.TextParameters(textAlign = TextAlign.Center)
        )
        Text.BodyOne(
            text = party.municipality,
            parameters = Text.TextParameters(textAlign = TextAlign.Center)
        )
        Text.BodyTwo(
            text = party.district,
            parameters = Text.TextParameters(textAlign = TextAlign.Center)
        )
        Text.BodyOne(
            text = party.dateRange,
            parameters = Text.TextParameters(textAlign = TextAlign.Center)
        )
    }
}
