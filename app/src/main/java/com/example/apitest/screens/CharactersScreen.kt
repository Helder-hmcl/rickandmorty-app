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

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apitest.R
import com.example.apitest.model.CharactersListDataModel
import com.example.apitest.viewModel.CharactersUiState

object CharactersScreen {
    @Composable
    fun CharactersScreen(
        charactersUiState: CharactersUiState,
        modifier: Modifier = Modifier,
        navController: NavController
    ) {
        when (charactersUiState) {
            is CharactersUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is CharactersUiState.Success -> ResultScreen(
                charactersUiState.characters,
                navController
            )

            is CharactersUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }

    /**
     * The home screen displaying the loading message.
     */
    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    /**
     * The home screen displaying error message with re-attempt button.
     */
    @Composable
    fun ErrorScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        }
    }

    /**
     * ResultScreen displaying number of characters retrieved.
     */
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun ResultScreen(
        characters: CharactersListDataModel,
        navController: NavController
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(characters.results) { character ->
                    Log.d("ASD", characters.results.size.toString())
                    GlideImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
