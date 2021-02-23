package com.example.darlibrary.di

import androidx.room.Room
import com.example.darlibrary.data.BookRepository
import com.example.darlibrary.data.db.Book
import com.example.darlibrary.data.db.BookDao
import com.example.darlibrary.data.db.BookRoomDatabase
import com.example.darlibrary.ui.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(androidApplication(), BookRoomDatabase::class.java, "book-database")
            .build()
    }

    fun getBookDao(database: BookRoomDatabase): BookDao {
        return database.bookDao()
    }

    single {
        getBookDao(
            database = get()
        )
    }

}