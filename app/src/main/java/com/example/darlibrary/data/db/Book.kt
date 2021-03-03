package com.example.darlibrary.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String,
    val createdAt: String,
    @ColumnInfo(name = "genre_id")  val genreId: Int,
    val isbn: String,
    @ColumnInfo(name = "publish_date")  val publishDate: String,
    val title: String,
    val updatedAt: String
)