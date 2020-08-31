package com.dagger.core.repository

import com.dagger.core.data.ForeCastPeriodItem

class ForeCastPeriodItemRepository (private val foreCastPeriodItemDataSource: ForeCastPeriodItemDataSource){

    suspend fun addNote(note: ForeCastPeriodItem) = foreCastPeriodItemDataSource.add(note)

    suspend fun getNote(id: Long) = foreCastPeriodItemDataSource.get(id)

    suspend fun getAllNotes() = foreCastPeriodItemDataSource.getAll()

    suspend fun removeNote(note : ForeCastPeriodItem) = foreCastPeriodItemDataSource.remove(note)
}