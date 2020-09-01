package com.dagger.weatherapp.framework.model.remotedata


import com.dagger.weatherapp.framework.di.DaggerApiComponent
import com.dagger.weatherapp.framework.model.entity.ApiMainEntity
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemResponse
import io.reactivex.Single
import javax.inject.Inject

class ForeCastPeriodApiService {

    @Inject
    lateinit var api: ForeCastPeriodApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getForeCastPeriod(grid : String) : Single<ForeCastPeriodItemResponse> {
        return api.getForeCastPeriod(grid)
    }

    fun getApiMainCall(latlong:String) : Single<ApiMainEntity> {
        return api.getApiMainCall(latlong)
    }
}