package com.dagger.weatherapp.framework.di

import com.dagger.weatherapp.framework.model.remotedata.ForeCastPeriodApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private  val BASE_URL: String = "https://api.weather.gov/"

    @Provides
    fun provideForeCastPeriodApi() : ForeCastPeriodApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ForeCastPeriodApi::class.java)
    }
}