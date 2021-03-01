package com.example.darlibrary.data.dto

data class BookRemoteDTO(
    val author: String,
    val createdAt: String,
    val genre_id: Any,
    val id: Int,
    val isbn: String,
    val publish_date: String,
    val title: String,
    val updatedAt: String
)