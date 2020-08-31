package com.dagger.core.repository

import com.dagger.core.data.ForeCastPeriodItem

class ForeCastPeriodItemRepository (private val foreCastPeriodItemDataSource: ForeCastPeriodItemDataSource){

    suspend fun insertAllForeCastPeriodItem(forecastPeriodItem: ForeCastPeriodItem) = foreCastPeriodItemDataSource.InsertAll(forecastPeriodItem)

    suspend fun getForeCastPeriodItem(id: Long) = foreCastPeriodItemDataSource.get(id)

    suspend fun getAllForeCastPeriodItems() = foreCastPeriodItemDataSource.getAll()

    suspend fun removeForeCastPeriodItem(forecastPeriod : ForeCastPeriodItem) = foreCastPeriodItemDataSource.remove(forecastPeriod)
}