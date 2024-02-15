package com.example.apitest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apitest.model.CharacterEntity

class RickAndMortyPagingSource(
    private val rickAndMortyApi: RickAndMortyApi,
) : PagingSource<Int, CharacterEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        return try {
            val page = params.key ?: 1
            val response =
                RickAndMortyRemoteMediator(rickAndMortyApi).getAllCharacters(
                    page,
                    20
                )
            LoadResult.Page(
                data = response.map { it },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
