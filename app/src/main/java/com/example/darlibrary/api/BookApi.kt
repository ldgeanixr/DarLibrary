package com.example.darlibrary.api

import com.example.darlibrary.data.dto.BookRemoteDTO
import retrofit2.http.GET

interface BookApi {

    @GET("api/books")
    suspend fun getAllBooks(): List<BookRemoteDTO>

}