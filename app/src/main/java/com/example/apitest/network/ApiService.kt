package com.example.apitest.network

import com.example.apitest.model.CharacterDataModel
import com.example.apitest.model.CharactersListDataModel
import com.example.apitest.model.EpisodesListDataModel
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path

// This interface defines an API
// service for getting random photos.
interface ApiService {
    // This annotation specifies that the HTTP method
    // is GET and the endpoint URL is "episode".
    // This method returns a Call object with a generic
    // type of DataModel, which represents
    // the data model for the response.
    @GET("episode")
    fun getEpisodes(): Call<EpisodesListDataModel>

    @GET("character")
    fun getCharacters(): Call<CharactersListDataModel>

    @GET("character/{characterId}")
    fun getSingleCharacter(
        @Path("characterId") characterId: String,
    ): Call<CharacterDataModel>
}
