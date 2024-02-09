package com.example.apitest.domain

import com.example.apitest.model.Location
import com.example.apitest.model.Origin
import javax.inject.Inject

data class Character @Inject constructor(
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