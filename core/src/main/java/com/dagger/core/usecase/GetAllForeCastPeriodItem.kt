package com.dagger.core.usecase

import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.core.repository.ForeCastPeriodItemRepository

class GetAllForeCastPeriodItem (private val foreCastPeriodItemRepository: ForeCastPeriodItemRepository) {

    suspend fun invoke(foreCastPeriodItem : ForeCastPeriodItem) = foreCastPeriodItemRepository.getAllForeCastPeriodItems()

}