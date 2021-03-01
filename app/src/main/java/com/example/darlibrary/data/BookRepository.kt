package com.example.darlibrary.data

import com.example.darlibrary.api.BookApi
import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.BookDao
import com.example.darlibrary.data.dto.toDomainModel
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val bookDao: BookDao,
    private val bookApi: BookApi
) {

    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks()
    }

    suspend fun getAllBooksFromApi(): List<Book> {
        return bookApi.getAllBooks().map{
            it.toDomainModel()
        }
    }

    suspend fun addBooks(books: List<Book>){
        bookDao.addBooks(books)
    }

    suspend fun generateBooksWithCount(count: Int = 10) {
        for (i in 1..count){
            val book = Book(name = "Book #$i", imageUrl = "Url #$i")
            bookDao.addBook(book)
        }
    }
}