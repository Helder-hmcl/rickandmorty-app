package com.example.apitest.data.mappers

import com.example.apitest.model.CharacterEntity
import com.example.apitest.model.EpisodeEntity
import com.example.apitest.data.remote.CharacterDto
import com.example.apitest.data.remote.EpisodeDto

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location,
        name = name,
        origin = origin,
        species = species,
        status = status,
        type = type,
        url = url,
    )
}

fun EpisodeDto.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        name = name,
        air_date = air_date,
        episode_code = episode_code,
        characters = characters,
        url = url,
        created = created
    )
}
