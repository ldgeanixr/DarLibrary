package com.example.darlibrary.data.dto

import com.example.darlibrary.data.db.Book

fun BookRemoteDTO.toDomainModel(): Book {
    return Book(
        id = id,
        name = title,
        imageUrl = ""
    )
}