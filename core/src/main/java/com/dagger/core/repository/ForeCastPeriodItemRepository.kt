package com.dagger.core.repository

import com.dagger.core.data.ForeCastPeriodItem

class ForeCastPeriodItemRepository (private val foreCastPeriodItemDataSource: ForeCastPeriodItemDataSource){

    suspend fun addForeCastPeriodItem(note: ForeCastPeriodItem) = foreCastPeriodItemDataSource.add(note)

    suspend fun getForeCastPeriodItem(id: Long) = foreCastPeriodItemDataSource.get(id)

    suspend fun getAllForeCastPeriodItems() = foreCastPeriodItemDataSource.getAll()

    suspend fun removeForeCastPeriodItem(note : ForeCastPeriodItem) = foreCastPeriodItemDataSource.remove(note)
}