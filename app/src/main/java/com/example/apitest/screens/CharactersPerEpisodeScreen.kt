package com.example.apitest.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    @Composable
    fun CharactersPerEpisodeScreen(charactersUiState: CharactersPerEpisodeUiState, modifier: Modifier) {
        when (charactersUiState) {
            is CharactersPerEpisodeUiState.Loading -> CharacterLoadingScreen(modifier = modifier.fillMaxSize())
            is CharactersPerEpisodeUiState.Success -> CharacterResultScreen(
                charactersUiState.characters,
                modifier = modifier.fillMaxWidth()
            )

            is CharactersPerEpisodeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun CharacterResultScreen(characters: List<CharacterDataModel>, modifier: Modifier) {
        Log.d("AQUIMESMO", characters.size.toString())

        for (each in characters) {
            Log.d("AQUIMESMO characterScreen", each.toString())
        }

        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(characters) { character ->
                Column {
                    GlideImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun CharacterLoadingScreen(modifier: Modifier) {
        Log.d("LoadingDebug", "CharacterLoadingScreen")
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}
