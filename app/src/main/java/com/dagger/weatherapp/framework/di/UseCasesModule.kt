package com.dagger.weatherapp.framework.di

import com.dagger.core.repository.ForeCastPeriodItemRepository
import com.dagger.core.usecase.AddForeCastPeriodItem
import com.dagger.core.usecase.GetAllForeCastPeriodItem
import com.dagger.core.usecase.GetForeCastPeriodItem
import com.dagger.core.usecase.RemoveForeCastPeriodItem
import com.dagger.weatherapp.framework.model.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository:ForeCastPeriodItemRepository) = UseCases(
        AddForeCastPeriodItem(repository),
        GetAllForeCastPeriodItem(repository),
        GetForeCastPeriodItem(repository),
        RemoveForeCastPeriodItem(repository)
    )
}