package com.example.apitest.data.remote

import android.util.Log
import com.example.apitest.model.CharacterEntity
import com.example.apitest.model.CharactersResponse
import com.example.apitest.model.EpisodesListDataModel
import com.example.apitest.model.Location
import com.example.apitest.model.Origin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyRemoteMediator(
    private val rickAndMortyApi: RickAndMortyApi
) {
    fun getAllEpisodes(
        callback: (EpisodesListDataModel) -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RickAndMortyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

        val call: Call<EpisodesListDataModel> = service.getAllEpisodes()

        call.enqueue(object : Callback<EpisodesListDataModel> {
            override fun onResponse(
                call: Call<EpisodesListDataModel>,
                response: Response<EpisodesListDataModel>
            ) {
                if (response.isSuccessful) {
                    val episodes: EpisodesListDataModel = response.body()!!
                    callback(episodes)
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<EpisodesListDataModel>, t: Throwable) {
                // Handle failure
            }
        })
    }

    suspend fun getAllCharacters(page: Int, pageCount: Int): List<CharacterEntity> {
        // Create a Retrofit instance with the base URL and
        // a GsonConverterFactory for parsing the response.
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RickAndMortyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

        val response: CharactersResponse = service.getAllCharacters(page, pageCount)

        // Call the getCharacters() method of the ApiService
        // to make an API request.
        return withContext(Dispatchers.IO) {
            service.getAllCharacters(page, pageCount).results
        }
    }

    suspend fun getSingleCharacter(characterId: String): CharacterDto =
        withContext(Dispatchers.IO) {
            // Create a Retrofit instance with the base URL and
            // a GsonConverterFactory for parsing the response.
            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(RickAndMortyApi.URL).addConverterFactory(
                    GsonConverterFactory.create(),
                ).build()

            // Create an ApiService instance from the Retrofit instance.
            val service: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

            // Call the getPhotos() method of the ApiService
            // to make an API request.

            val call: Call<CharacterDto> = service.getSingleCharacter(characterId)
            val response = call.execute()
            return@withContext if (response != null && response.isSuccessful && response.body() != null) {
                val character: CharacterDto = response.body()!!
                character
            } else {
                Log.d("TAG", "Error on getSingleCharacter")
                val location = Location("", "")
                val origin = Origin("", "")
                CharacterDto("", listOf(), "", 0, "", location, "", origin, "", "", "", "")
            }
        }
}