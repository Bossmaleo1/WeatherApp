package com.dagger.weatherapp.framework.model.entity

import com.google.gson.annotations.SerializedName

data class PropertiesEntity(
    @SerializedName("updated")
    val updated: String,
    @SerializedName("units")
    val units: String,
    @SerializedName("forecastGenerator")
    val forecastGenerator: String,
    @SerializedName("generatedAt")
    val generatedAt: String,
    @SerializedName("updateTime")
    val updateTime: String,
    @SerializedName("validTimes")
    val validTimes: String,
    @SerializedName("elevation")
    val elevation: ElevationEntity,
    @SerializedName("periods")
    val periods: List<ForeCastPeriodItemEntity>
)