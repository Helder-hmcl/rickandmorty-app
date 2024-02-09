package com.example.apitest.data.local

import javax.inject.Inject

data class EpisodeEntity @Inject constructor(
    var id: Int,
    var name: String,
    var air_date: String,
    var episode_code: String,
    var characters: Array<String>,
    var url: String,
    val created: String,
) : java.io.Serializable

data class EpisodesListDataModel @Inject constructor(
    var results: List<EpisodeEntity>,
) : java.io.Serializable
