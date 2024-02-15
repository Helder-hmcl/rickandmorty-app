package com.example.apitest.data.remote

import com.example.apitest.model.CharactersResponse
import com.example.apitest.model.EpisodesListDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): CharactersResponse

    companion object {
        const val TAG = "ApiCall"
        const val URL = "https://rickandmortyapi.com/api/"
    }

    // This function takes a Context and callback function
    // as a parameter, which will be called
    // when the API response is received.

    @GET("episode") // Adjust the URL as per your API endpoint
    fun getAllEpisodes(): Call<EpisodesListDataModel>

    @GET("character/{characterId}")
    fun getSingleCharacter(
        @Path("characterId") characterId: String,
    ): Call<CharacterDto>
}
