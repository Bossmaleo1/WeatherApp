package com.dagger.weatherapp.framework.di

import com.dagger.weatherapp.framework.model.remotedata.ForeCastPeriodApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(apiService: ForeCastPeriodApiService)
}