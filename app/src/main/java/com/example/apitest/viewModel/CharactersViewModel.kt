package com.example.apitest.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.model.CharactersListDataModel
import com.example.apitest.network.ApiCall
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CharactersUiState {
    data class Success(val characters: CharactersListDataModel) : CharactersUiState
    data object Error : CharactersUiState
    data object Loading : CharactersUiState
}
data class ScreenState(
    val isLoading: Boolean = false,
    val items: CharactersListDataModel = CharactersListDataModel(emptyList()),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    var charactersUiState: CharactersUiState by mutableStateOf(CharactersUiState.Loading)
        private set

    var state by mutableStateOf(ScreenState())

/*    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            ApiCall().getAllCharacters({}, nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )*/

    init {
        val context = getApplication<Application>().applicationContext
        getCharacters(context)
    }

    private fun getCharacters(context: Context) {
        viewModelScope.launch {
            charactersUiState = CharactersUiState.Loading
            try {
                var listResult: CharactersListDataModel? = null

                ApiCall().getAllCharacters() { characters ->
                    listResult = characters
                    charactersUiState = CharactersUiState.Success(listResult!!)
                }
            } catch (e: IOException) {
                EpisodeUiState.Error
            } catch (e: HttpException) {
                EpisodeUiState.Error
            }
        }
    }
}
