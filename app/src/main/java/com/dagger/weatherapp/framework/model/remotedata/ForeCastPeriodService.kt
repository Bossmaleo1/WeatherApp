package com.dagger.weatherapp.framework.model.remotedata

import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ForeCastPeriodService {

    private  val BASE_URL: String = "https://api.weather.gov/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ForeCastPeriodApi::class.java)

    fun getForeCastPeriod(grid : String) : Single<List<ForeCastPeriodItemEntity>> {
        return api.getForeCastPeriod(grid)
    }
}