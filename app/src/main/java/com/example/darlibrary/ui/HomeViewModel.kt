package com.example.darlibrary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.darlibrary.data.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val books = bookRepository.getAllBooksFromApi()
            bookRepository.addBooks(books)
        }
    }

    val books = bookRepository.getAllBooks().asLiveData()
}


sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}