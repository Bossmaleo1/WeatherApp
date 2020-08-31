package com.dagger.weatherapp.framework.model.remotedata

import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ForeCastPeriodApi {

    @GET("/gridpoints/OKX/{grid}/forecast")
    fun getForeCastPeriod(@Path("grid")grid:String): Single<List<ForeCastPeriodItemEntity>>
}