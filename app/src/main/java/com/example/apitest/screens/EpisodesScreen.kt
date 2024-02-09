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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apitest.R
import com.example.apitest.data.local.EpisodesListDataModel
import com.example.apitest.screens.elements.HorizontalGridCard
import com.example.apitest.viewModel.CharactersPerEpisodeUrlListHolder
import com.example.apitest.viewModel.EpisodeUiState

object EpisodesScreen {
    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun EpisodesScreen(
        episodeUiState: EpisodeUiState,
        modifier: Modifier = Modifier,
        navController: NavController,
    ) {
        when (episodeUiState) {
            is EpisodeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is EpisodeUiState.Success ->
                ResultScreen(
                    episodeUiState.episodes,
                    navController,
                )

            is EpisodeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }

    @Suppress("ktlint:standard:function-naming")
    /**
     * The home screen displaying the loading message.
     */
    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
        )
    }

    @Suppress("ktlint:standard:function-naming")
    /**
     * The home screen displaying error message with re-attempt button.
     */
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

    @Suppress("ktlint:standard:function-naming")
    /**
     * ResultScreen displaying number of episodes retrieved.
     */
    @Composable
    fun ResultScreen(
        episodes: EpisodesListDataModel,
        navController: NavController,
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
            ) {
                items(episodes.results) { episode ->
                    HorizontalGridCard(title = episode.name, description = episode.air_date) {
                        CharactersPerEpisodeUrlListHolder.listOfCharactersUrl =
                            episode.characters.toMutableList()
                        navController.navigate("charactersPerEpisode")
                    }
                }
            }
        }
    }
}
