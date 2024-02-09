package com.example.apitest.data.mappers

import com.example.apitest.data.local.CharacterEntity
import com.example.apitest.data.local.EpisodeEntity
import com.example.apitest.data.remote.CharacterDto
import com.example.apitest.data.remote.EpisodeDto
import com.example.apitest.domain.Character
import com.example.apitest.domain.Episode

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

fun CharacterEntity.toCharacter(): Character {
    return Character(
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

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        air_date = air_date,
        episode_code = episode_code,
        characters = characters,
        url = url,
        created = created
    )
}
