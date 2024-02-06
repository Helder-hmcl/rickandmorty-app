package com.example.apitest.model

data class EpisodeDataModel(
    var id: Int,
    var name: String,
    var air_date: String,
    var episode_code: String,
    var characters: Array<String>,
    var url: String,
    val created: String,
) : java.io.Serializable

data class EpisodesListDataModel(
    var results: List<EpisodeDataModel>,
) : java.io.Serializable
