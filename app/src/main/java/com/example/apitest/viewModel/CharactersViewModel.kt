package com.example.apitest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.apitest.data.local.CharacterEntity
import com.example.apitest.data.mappers.toCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

sealed interface CharactersUiState {
    data class Success(val characters: List<CharacterEntity>) : CharactersUiState

    object Error : CharactersUiState

    object Loading : CharactersUiState
}

@HiltViewModel
class CharactersViewModel @Inject constructor(
    pager: Pager<Int, CharacterEntity>
) : ViewModel() {

    val characterPagingFlow = pager.flow.map { pagingData ->
        pagingData.map { it.toCharacter() }
    }.cachedIn(viewModelScope)
}
