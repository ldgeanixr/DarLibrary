package com.example.darlibrary.api

import com.example.darlibrary.data.dto.BookRemoteDTO
import com.example.darlibrary.data.dto.GenreRemoteDTO
import retrofit2.http.GET

interface BookApi {

    @GET("api/books")
    suspend fun getAllBooks(): List<BookRemoteDTO>

    @GET("api/genres")
    suspend fun getAllGenres(): List<GenreRemoteDTO>

}