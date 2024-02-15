package com.example.apitest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apitest.model.CharacterEntity
import com.example.apitest.screens.elements.CharacterCard
import com.example.apitest.viewModel.CharactersPerEpisodeUiState

object CharactersPerEpisodeScreen {
    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharactersPerEpisodeScreen(
        charactersUiState: CharactersPerEpisodeUiState,
    ) {
        when (charactersUiState) {
            is CharactersPerEpisodeUiState.Loading -> CharacterLoadingScreen()
            is CharactersPerEpisodeUiState.Success ->
                CharacterResultScreen(
                    charactersUiState.characters,
                )

            is CharactersPerEpisodeUiState.Error -> CharacterLoadingScreen()
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharacterResultScreen(
        characters: List<CharacterEntity>,
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(characters) { character ->
                CharacterCard(character.name, character.image)
            }
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun CharacterLoadingScreen() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
}
