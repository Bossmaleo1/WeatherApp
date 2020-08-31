package com.dagger.weatherapp.framework.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class DatabaseService : RoomDatabase()  {

    companion object {
        private const val DATABASE_NAME = "weather.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    //abstract fun noteDAO(): NoteDAO
}