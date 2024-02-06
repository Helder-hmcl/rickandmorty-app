package com.example.apitest.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apitest.R
import com.example.apitest.model.CharacterDataModel
import com.example.apitest.screens.CharactersScreen.ErrorScreen
import com.example.apitest.viewModel.CharactersPerEpisodeUiState

object CharactersPerEpisodeScreen {
    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharactersPerEpisodeScreen(
        charactersUiState: CharactersPerEpisodeUiState,
        modifier: Modifier,
    ) {
        when (charactersUiState) {
            is CharactersPerEpisodeUiState.Loading -> CharacterLoadingScreen(modifier = modifier.fillMaxSize())
            is CharactersPerEpisodeUiState.Success ->
                CharacterResultScreen(
                    charactersUiState.characters,
                    modifier = modifier.padding(8.dp),
                )

            is CharactersPerEpisodeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun CharacterResultScreen(
        characters: List<CharacterDataModel>,
        modifier: Modifier,
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(characters) { character ->
                Column {
                    GlideImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = modifier,
                    )
                }
            }
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharacterLoadingScreen(modifier: Modifier) {
        Log.d("LoadingDebug", "CharacterLoadingScreen")
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
        )
    }
}
