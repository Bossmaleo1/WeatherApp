package com.dagger.core.usecase

import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.core.repository.ForeCastPeriodItemRepository

class AddForeCastPeriodItem(private val foreCastPeriodItemRepository: ForeCastPeriodItemRepository) {

    suspend fun invoke(foreCastPeriodItem : ForeCastPeriodItem) = foreCastPeriodItemRepository.addForeCastPeriodItem(foreCastPeriodItem)
}