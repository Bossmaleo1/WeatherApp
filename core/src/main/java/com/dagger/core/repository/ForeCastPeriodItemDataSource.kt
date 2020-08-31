package com.dagger.core.repository

import com.dagger.core.data.ForeCastPeriodItem

interface ForeCastPeriodItemDataSource {

    suspend fun add(foreCastPeriodItem: ForeCastPeriodItem)

    suspend fun get(id: Long) : ForeCastPeriodItem?

    suspend fun getAll() : List<ForeCastPeriodItem>

    suspend fun remove(foreCastPeriodItem: ForeCastPeriodItem)
}