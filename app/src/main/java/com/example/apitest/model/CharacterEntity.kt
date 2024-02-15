package com.example.apitest.model

data class CharacterEntity(
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

data class CharactersResponse(
    val info: Info,
    var results: List<CharacterEntity>,
) : java.io.Serializable

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) : java.io.Serializable
