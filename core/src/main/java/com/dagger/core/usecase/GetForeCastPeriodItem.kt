package com.dagger.core.usecase

import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.core.repository.ForeCastPeriodItemRepository

class GetForeCastPeriodItem (private val foreCastPeriodItemRepository: ForeCastPeriodItemRepository) {

    suspend operator fun invoke(id : Long) = foreCastPeriodItemRepository.getForeCastPeriodItem(id)

}