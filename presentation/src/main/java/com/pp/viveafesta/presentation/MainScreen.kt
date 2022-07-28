package com.pp.viveafesta.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pp.design.background.LocalBaseColor
import com.pp.design.large.appbars.AppBar
import com.pp.design.small.button.Button
import com.pp.design.small.icon.Icon
import com.pp.design.small.text.Text
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination by derivedStateOf { navBackStackEntry?.destination?.route }
    val scope = rememberCoroutineScope()

    // shared viewModels
    val addPartyViewModel = hiltViewModel<AddPartyViewModel>()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(onFilterClicked = { navController.navigate(FilterPartyScreen) })
            Divider(Modifier.fillMaxWidth())
            NavHost(navController = navController, startDestination = PartyListScreen) {
                composable(PartyListScreen) {
                    PartyScreen()
                }
                composable(AddPartyScreen) {
                    AddPartyScreen(
                        viewModel = addPartyViewModel,
                        onCameraOpenRequested = { navController.navigate(CameraScreen) },
                        onPartyAdded = { navController.navigate(PartyListScreen) }
                    )
                }
                composable(FilterPartyScreen) {
                    FilterPartyScreen()
                }
                composable(CameraScreen) {
                    CameraScreen(
                        viewModel = addPartyViewModel,
                        executor = Executors.newSingleThreadExecutor(),
                        onImageCaptured = { exception ->
                            scope.launch {
                                exception?.printStackTrace()
                                navController.navigate(AddPartyScreen)
                            }
                        }
                    )
                }
            }
        }

        AddPartyButton(
            isVisible = currentDestination == PartyListScreen,
            onClick = { navController.navigate(AddPartyScreen) }
        )
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
private fun BoxScope.AddPartyButton(isVisible: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(start = 16.dp)
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button.Fab(
                outerModifier = Modifier.padding(16.dp),
                text = "Adicionar festa",
                backgroundColor = LocalBaseColor.current,
                onClick = onClick
            )
        }
    }
}
