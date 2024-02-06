package com.example.apitest.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.model.EpisodesListDataModel
import com.example.apitest.network.ApiCall
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface EpisodeUiState {
    data class Success(val episodes: EpisodesListDataModel) : EpisodeUiState
    data object Error : EpisodeUiState
    data object Loading : EpisodeUiState
}

class EpisodesViewModel(application: Application) : AndroidViewModel(application) {

    var episodeUiState: EpisodeUiState by mutableStateOf(EpisodeUiState.Loading)
        private set

    init {
        val context = getApplication<Application>().applicationContext
        getEpisodes(context)
    }

    private fun getEpisodes(context: Context) {
        viewModelScope.launch {
            episodeUiState = EpisodeUiState.Loading
            try {
                var listResult: EpisodesListDataModel?

                ApiCall().getAllEpisodes(context) { episodes ->
                    listResult = episodes
                    episodeUiState = EpisodeUiState.Success(listResult!!)
                }
            } catch (e: IOException) {
                EpisodeUiState.Error
            } catch (e: HttpException) {
                EpisodeUiState.Error
            }
        }
    }
}
