package com.example.darlibrary.di

import android.widget.SearchView
import com.example.darlibrary.data.BookRepository
import com.example.darlibrary.ui.HomeViewModel
import com.example.darlibrary.ui.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        BookRepository(
            bookDao = get(),
            bookApi = get()
        )
    }

    viewModel {
        HomeViewModel(
            bookRepository = get()
        )
    }

    viewModel {
        SearchViewModel(
            bookRepository = get()
        )
    }



}