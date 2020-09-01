package com.dagger.weatherapp.framework.di

import com.dagger.weatherapp.framework.viewmodel.ForeCastPeriodViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class,RepositoryModule::class,UseCasesModule::class])
interface ViewModelComponent {
    fun inject(foreCastPeriodViewModel: ForeCastPeriodViewModel)
}