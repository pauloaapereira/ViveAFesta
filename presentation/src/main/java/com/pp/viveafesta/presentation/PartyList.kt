package com.pp.viveafesta.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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


@Composable
fun PartyList(viewModel: MainViewModel, contentPadding: PaddingValues) {
    LazyColumn(contentPadding = contentPadding) {
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
                modifier = Modifier.requiredSize(
                    width = configuration.screenWidthDp.dp * .8f,
                    height = 400.dp
                ),
                model = it, contentDescription = "cartaz"
            )
        }
        Text.BodyOne(
            text = party.name,
            parameters = Text.TextParameters(textAlign = TextAlign.Center)
        )
    }
}
