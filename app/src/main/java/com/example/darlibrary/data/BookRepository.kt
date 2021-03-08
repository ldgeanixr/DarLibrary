package com.example.darlibrary.data

import com.example.darlibrary.api.BookApi
import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.BookDao
import com.example.darlibrary.data.db.Genre
import com.example.darlibrary.data.dto.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepository(
    private val bookDao: BookDao,
    private val bookApi: BookApi
) {

    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks()
    }

    suspend fun getAllBooksFromApi(): Flow<List<Book>> {
        return flow {
            val remoteBooks = bookApi.getAllBooks()
                .map { books -> books.toDomainModel() }
            emit(remoteBooks)
            addBooks(remoteBooks)
        }
    }

    suspend fun getGenresFromApi() : List<Genre>{
        return bookApi.getAllGenres()
            .map{ genres -> genres.toDomainModel() }
    }

    suspend fun addBooks(books: List<Book>){
        bookDao.addBooks(books)
    }

}