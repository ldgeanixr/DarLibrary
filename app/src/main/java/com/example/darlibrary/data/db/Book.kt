package com.example.darlibrary.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var imageUrl: String
)