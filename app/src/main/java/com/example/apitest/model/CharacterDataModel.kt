package com.example.apitest.model

data class CharacterDataModel(
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
    val url: String
)

data class CharactersListDataModel(
    var results: List<CharacterDataModel>
):java.io.Serializable