package com.dagger.weatherapp.framework.model.remotedata

import com.dagger.weatherapp.framework.model.entity.ApiMainEntity
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ForeCastPeriodApi {

    @GET("/{grid}")
    fun getForeCastPeriod(@Path("grid")grid:String): Single<ForeCastPeriodItemResponse>

    @GET("/points/{latlong}")
    fun getApiMainCall(@Path("latlong")latlong:String): Single<ApiMainEntity>
}