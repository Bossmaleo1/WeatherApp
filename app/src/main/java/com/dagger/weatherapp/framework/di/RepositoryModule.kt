package com.dagger.weatherapp.framework.di

import android.app.Application
import androidx.room.RoomDatabase
import com.dagger.core.repository.ForeCastPeriodItemRepository
import com.dagger.weatherapp.framework.model.db.RoomForeCastPeriodDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
   fun provideRepository(app: Application) = ForeCastPeriodItemRepository(RoomForeCastPeriodDataSource(app))
}