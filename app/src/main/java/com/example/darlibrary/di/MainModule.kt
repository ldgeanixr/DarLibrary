package com.example.darlibrary.di

import com.example.darlibrary.data.BookRepository
import com.example.darlibrary.ui.HomeViewModel
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

}