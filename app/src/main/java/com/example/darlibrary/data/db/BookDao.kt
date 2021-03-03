package com.example.darlibrary.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(bookItem: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBooks(books: List<Book>)

    @Query("DELETE from book_table")
    suspend fun deleteAllBooks()

}