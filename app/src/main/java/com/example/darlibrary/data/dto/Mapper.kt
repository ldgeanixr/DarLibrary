package com.example.darlibrary.data.dto

import com.example.darlibrary.data.db.Book

fun BookRemoteDTO.toDomainModel(): Book {
    return Book(
        id = id,
        author = author,
        createdAt = createdAt,
        genreId = genre_id,
        isbn = isbn,
        publishDate = publish_date,
        title = title,
        updatedAt = updatedAt
    )
}