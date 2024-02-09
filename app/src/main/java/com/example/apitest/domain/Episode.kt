package com.example.apitest.domain

import javax.inject.Inject


data class Episode @Inject constructor(
    var id: Int,
    var name: String,
    var air_date: String,
    var episode_code: String,
    var characters: Array<String>,
    var url: String,
    val created: String,
)