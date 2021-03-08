package com.example.darlibrary.data.dto

import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.Genre

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

fun GenreRemoteDTO.toDomainModel(): Genre {
    return Genre(
        id = id,
        title = title,
        createdAt = createdAt,
        updatedAt = updatedAt,
        enabled = enabled,
        sort = sort
    )
}