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
package com.pp.ppcompose.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pp.design.background.Background
import com.pp.design.core.theme.ComposifyTheme
import com.pp.design.small.text.Text.BodyOne
import com.pp.design.small.text.Text.HeaderFour
import com.pp.design.small.text.Text.TextParameters
import com.pp.ppcompose.presentation.models.ViewParty
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposifyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(viewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchParties()
    }

    Background {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderFour(
                    text = "Procuras uma festa?", parameters = TextParameters(
                        textAlign = TextAlign.Center
                    )
                )
                LazyColumn {
                    items(viewModel.parties) { party ->
                        Party(party)
                        Divider(Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
fun Party(party: ViewParty) {
    val configuration = LocalConfiguration.current

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        party.poster?.let {
            AsyncImage(modifier = Modifier.requiredSize(width = configuration.screenWidthDp.dp * .8f, height = 400.dp), model = it, contentDescription = "cartaz")
        }
        BodyOne(text = party.name, parameters = TextParameters(textAlign = TextAlign.Center))
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    ComposifyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    ComposifyTheme(darkTheme = true) {
        MyApp()
    }
}
