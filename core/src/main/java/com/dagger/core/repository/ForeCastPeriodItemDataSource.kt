package com.dagger.core.repository

import com.dagger.core.data.ForeCastPeriodItem

interface ForeCastPeriodItemDataSource {

    suspend fun InsertAll(foreCastPeriodItem: ForeCastPeriodItem) : List<Long>

    suspend fun get(id: Long) : ForeCastPeriodItem?

    suspend fun getAll() : List<ForeCastPeriodItem>

    suspend fun remove(foreCastPeriodItem: ForeCastPeriodItem)
}