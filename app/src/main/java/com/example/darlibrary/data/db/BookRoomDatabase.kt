package com.example.darlibrary.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 2)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}