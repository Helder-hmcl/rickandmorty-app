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
package com.example.apitest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.apitest.R
import com.example.apitest.screens.elements.CharacterCard
import com.example.apitest.viewModel.CharactersViewModel


object CharactersScreen {
    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharactersScreen() {
        PagingListScreen()
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun LoadingScreen() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(color = Color.Black)
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun ErrorScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = "",
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun PagingListScreen() {
        val viewModel = hiltViewModel<CharactersViewModel>()
        val characters = viewModel.characterPagingFlow.collectAsLazyPagingItems()

        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(
                count = characters.itemCount,
                key = characters.itemKey { it.id }
            ) { i ->
                CharacterCard(characters[i]?.name ?: "", characters[i]?.image ?: "")
            }

            when (val state = characters.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    item { ErrorScreen() }
                }

                is LoadState.Loading -> { // Loading UI
                    item {
                        LoadingScreen()
                    }
                }

                else -> {
                }
            }

            when (val state = characters.loadState.append) { // Pagination
                is LoadState.Error -> {
                    item { ErrorScreen() }
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        LoadingScreen()
                    }
                }

                else -> {}
            }
        }
    }
}