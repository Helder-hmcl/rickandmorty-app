package com.example.apitest.data.remote

import com.example.apitest.model.Location
import com.example.apitest.model.Origin


data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
)

data class CharactersListDataModel(
    var results: List<CharacterDto>,
) : java.io.Serializable


data class EpisodeDto(
    var id: Int,
    var name: String,
    var air_date: String,
    var episode_code: String,
    var characters: Array<String>,
    var url: String,
    val created: String,
)