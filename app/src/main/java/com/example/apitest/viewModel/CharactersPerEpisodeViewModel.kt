package com.example.apitest.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.model.CharacterDataModel
import com.example.apitest.network.ApiCall
import com.example.apitest.viewModel.CharactersPerEpisodeUrlListHolder.listOfCharactersUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface CharactersPerEpisodeUiState {
    data class Success(val characters: List<CharacterDataModel>) : CharactersPerEpisodeUiState
    data object Error : CharactersPerEpisodeUiState
    data object Loading : CharactersPerEpisodeUiState
}

object CharactersPerEpisodeUrlListHolder {
    var listOfCharactersUrl = mutableListOf<String>()
}

class CharactersPerEpisodeViewModel(application: Application) : AndroidViewModel(application) {

    var charactersPerEpisodeUiState: CharactersPerEpisodeUiState by mutableStateOf(CharactersPerEpisodeUiState.Loading)
        private set

    init {
        val context = getApplication<Application>().applicationContext
        getCharactersFromEpisode(context, listOfCharactersUrl)
    }


    private fun getCharactersFromEpisode(context: Context, listOfCharactersUrl: List<String>) {

        viewModelScope.launch {
            charactersPerEpisodeUiState = CharactersPerEpisodeUiState.Loading
            val listResult: MutableList<CharacterDataModel> = mutableListOf()
            try {
                val treatedList = mutableListOf<String>()

                listOfCharactersUrl.map {
                    val id = extractCharacterId(it)
                    treatedList.add(id.toString())
                }

                val deferredList = treatedList.map {
                    async {
                        val character = ApiCall().getSingleCharacter(it)
                        listResult.add(character)
                    }
                }

                deferredList.awaitAll()
                charactersPerEpisodeUiState = CharactersPerEpisodeUiState.Success(listResult)

            } catch (e: IOException) {
                CharactersPerEpisodeUiState.Error
            } catch (e: HttpException) {
                CharactersPerEpisodeUiState.Error
            }
        }
    }

    private fun extractCharacterId(characterUrl: String): String? {
        val regex = Regex("/(\\d+)$")
        val matchResult = regex.find(characterUrl)
        return matchResult?.groupValues?.getOrNull(1)
    }

}
