package com.example.apitest.network

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
