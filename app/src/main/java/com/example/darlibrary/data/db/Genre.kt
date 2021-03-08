package com.example.darlibrary.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class Genre(
    @PrimaryKey
    val id: Int,
    val title: String,
    val createdAt: String,
    val updatedAt: String,
    val enabled: Boolean,
    val sort: Int
)