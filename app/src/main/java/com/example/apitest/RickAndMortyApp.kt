/*
 * Copyright (C) 2023 The Android Open Source Project
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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apitest

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apitest.screens.CharactersPerEpisodeScreen
import com.example.apitest.screens.CharactersScreen
import com.example.apitest.screens.DrawerContent
import com.example.apitest.screens.EpisodesScreen
import com.example.apitest.screens.HomeScreen
import com.example.apitest.viewModel.CharactersPerEpisodeViewModel
import com.example.apitest.viewModel.CharactersViewModel
import com.example.apitest.viewModel.EpisodesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyApp(applicationContext: Context) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        gesturesEnabled = true,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { DrawerContent.DrawerContent(navController, drawerState, scope) }
        }
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                RickAndMortyTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navController = navController,
                    scope = scope,
                    drawerState = drawerState
                )
            },
            bottomBar = {
                RickAndMortyBottomAppBar()
            },

            floatingActionButton = {
            }
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                MyApp(Modifier, navController, applicationContext, scope, drawerState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            scrolledContainerColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.inversePrimary,
            actionIconContentColor = MaterialTheme.colorScheme.inversePrimary
        ),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.inversePrimary,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Localized description"
                )
            }
        },
        navigationIcon = {
            Log.d("Debug", navController.currentBackStackEntry?.destination?.route.toString())
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
            }
        }
    )
}

enum class BottomIcons {
    HOME,
    EPISODES,
    CHARACTERS,
    FAVORITES
}

@Composable
fun RickAndMortyBottomAppBar() {
    val selected = remember { mutableStateOf(BottomIcons.HOME) }
    val iconPressedColor = MaterialTheme.colorScheme.primary
    val iconDefaultColor = MaterialTheme.colorScheme.onSurface
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        MaterialTheme.colorScheme.surface,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row() {
                        IconButton(onClick = { selected.value = BottomIcons.HOME }, Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Rounded.Home,
                                contentDescription = null,
                                tint = if (selected.value == BottomIcons.HOME) iconPressedColor else iconDefaultColor
                            )
                        }
                        IconButton(onClick = { selected.value = BottomIcons.EPISODES }, Modifier.weight(1f)) {
                            Icon(
                                Icons.Rounded.DateRange,
                                contentDescription = null,
                                tint = if (selected.value == BottomIcons.EPISODES) iconPressedColor else iconDefaultColor
                            )
                        }
                        IconButton(onClick = { selected.value = BottomIcons.CHARACTERS }, Modifier.weight(1f)) {
                            Icon(
                                Icons.Rounded.Face,
                                contentDescription = null,
                                tint = if (selected.value == BottomIcons.CHARACTERS) iconPressedColor else iconDefaultColor
                            )
                        }
                        IconButton(onClick = { selected.value = BottomIcons.FAVORITES }, Modifier.weight(1f)) {
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = null,
                                tint = if (selected.value == BottomIcons.FAVORITES) iconPressedColor else iconDefaultColor
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MyApp(
    modifier: Modifier,
    navController: NavHostController,
    applicationContext: Context,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen.HomeScreen(
                context = applicationContext,
                navController = navController,
                scope = scope,
                drawerState = drawerState
            )
        }
        composable("episodes") {
            val episodesViewModel: EpisodesViewModel = viewModel()
            EpisodesScreen.EpisodesScreen(
                episodeUiState = episodesViewModel.episodeUiState,
                navController = navController
            )
        }
        composable("charactersPerEpisode") {
            val charactersPerEpisodeViewModel: CharactersPerEpisodeViewModel = viewModel()
            CharactersPerEpisodeScreen.CharactersPerEpisodeScreen(
                charactersUiState = charactersPerEpisodeViewModel.charactersPerEpisodeUiState,
                modifier = modifier
            )
        }
        composable("characters") {
            val charactersViewModel: CharactersViewModel = viewModel()
            CharactersScreen.CharactersScreen(
                charactersUiState = charactersViewModel.charactersUiState,
                modifier = modifier,
                navController
            )
        }
    }
}
