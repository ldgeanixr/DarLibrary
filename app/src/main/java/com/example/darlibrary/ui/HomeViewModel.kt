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
            bookRepository.generateBooksWithCount(20)
        }
    }

    val books = bookRepository.getAllBooks().asLiveData()

}