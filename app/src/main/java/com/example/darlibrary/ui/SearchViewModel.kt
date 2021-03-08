package com.example.darlibrary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.darlibrary.data.BookRepository
import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _remoteBookState = MutableLiveData<Resource<List<Book>>>()
    val remoteBookState: LiveData<Resource<List<Book>>> = _remoteBookState

    val remoteGenreState = MutableLiveData<List<Genre>>()

    val books = bookRepository.getAllBooks().asLiveData()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getAllBooksFromApi()
                .onStart { _remoteBookState.postValue(Resource.Loading) }
                .catch { exception -> _remoteBookState.postValue(
                    Resource.Error(exception.message ?: "network error")
                ) }
                .collect { remoteBooks ->
                    _remoteBookState.postValue(Resource.Success(remoteBooks))
                }
        }
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = bookRepository.getGenresFromApi()
            remoteGenreState.postValue(genres)
        }
    }
}