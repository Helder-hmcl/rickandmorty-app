package com.example.apitest.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.apitest.model.CharacterDataModel
import com.example.apitest.model.CharactersListDataModel
import com.example.apitest.model.EpisodesListDataModel
import com.example.apitest.model.Location
import com.example.apitest.model.Origin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit

class ApiCall {
    companion object {
        const val TAG = "ApiCall"
        const val URL = "https://rickandmortyapi.com/api/"
    }

    // This function takes a Context and callback function
    // as a parameter, which will be called
    // when the API response is received.
    fun getAllEpisodes(
        context: Context,
        callback: (EpisodesListDataModel) -> Unit,
    ) {
        // Create a Retrofit instance with the base URL and
        // a GsonConverterFactory for parsing the response.

        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(URL).addConverterFactory(
                GsonConverterFactory.create(),
            ).build()

        // Create an ApiService instance from the Retrofit instance.
        val service: ApiService = retrofit.create(ApiService::class.java)

        // Call the getPhotos() method of the ApiService
        // to make an API request.
        val call: Call<EpisodesListDataModel> = service.getEpisodes()

        // Use the enqueue() method of the Call object to
        // make an asynchronous API request.
        call.enqueue(
            object : Callback<EpisodesListDataModel> {
                // This is an anonymous inner class that implements the Callback interface.
                override fun onResponse(
                    response: Response<EpisodesListDataModel>?,
                    retrofit: Retrofit?,
                ) {
                    // This method is called when the API response is received successfully.
                    if (response!!.isSuccess) {
                        // If the response is successful, parse the
                        // response body to a DataModel object.
                        val episodes: EpisodesListDataModel =
                            response.body() as EpisodesListDataModel
                        // Call the callback function with the DataModel
                        // object as a parameter.

                        callback(episodes)
                    }
                }

                override fun onFailure(t: Throwable?) {
                    // This method is called when the API request fails.
                    Log.e(TAG, t.toString())
                    Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    fun getAllCharacters(callback: (CharactersListDataModel) -> Unit) {
        // Create a Retrofit instance with the base URL and
        // a GsonConverterFactory for parsing the response.

        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(URL).addConverterFactory(
                GsonConverterFactory.create(),
            ).build()

        // Create an ApiService instance from the Retrofit instance.
        val service: ApiService = retrofit.create(ApiService::class.java)

        // Call the getPhotos() method of the ApiService
        // to make an API request.
        val call: Call<CharactersListDataModel> = service.getCharacters()

        // Use the enqueue() method of the Call object to
        // make an asynchronous API request.
        call.enqueue(
            object : Callback<CharactersListDataModel> {
                // This is an anonymous inner class that implements the Callback interface.
                override fun onResponse(
                    response: Response<CharactersListDataModel>?,
                    retrofit: Retrofit?,
                ) {
                    // This method is called when the API response is received successfully.
                    if (response!!.isSuccess) {
                        // If the response is successful, parse the
                        // response body to a DataModel object.
                        val episodes: CharactersListDataModel =
                            response.body() as CharactersListDataModel
                        // Call the callback function with the DataModel
                        // object as a parameter.

                        callback(episodes)
                    }
                }

                override fun onFailure(t: Throwable?) {
                    // This method is called when the API request fails.
                    Log.e(TAG, t.toString())
                }
            },
        )
    }

    suspend fun getSingleCharacter(characterId: String): CharacterDataModel =
        withContext(Dispatchers.IO) {
            // Create a Retrofit instance with the base URL and
            // a GsonConverterFactory for parsing the response.
            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(URL).addConverterFactory(
                    GsonConverterFactory.create(),
                ).build()

            // Create an ApiService instance from the Retrofit instance.
            val service: ApiService = retrofit.create(ApiService::class.java)

            // Call the getPhotos() method of the ApiService
            // to make an API request.

            val call: Call<CharacterDataModel> = service.getSingleCharacter(characterId)
            val response = call.execute()
            return@withContext if (response != null && response.isSuccess && response.body() != null) {
                val character: CharacterDataModel = response.body()!!
                character
            } else {
                Log.d(TAG, "Error on getSingleCharacter")
                val location = Location("", "")
                val origin = Origin("", "")
                CharacterDataModel("", listOf(), "", 0, "", location, "", origin, "", "", "", "")
                // Handle unsuccessful response or null body
            }
        }
}
