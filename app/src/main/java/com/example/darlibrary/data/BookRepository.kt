package com.example.darlibrary.data

import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.BookDao
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val bookDao: BookDao
) {

    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks()
    }

    suspend fun generateBooksWithCount(count: Int = 10) {
        for (i in 1..count){
            val book = Book(name = "Book #$i", imageUrl = "Url #$i")
            bookDao.addBook(book)
        }
    }
}