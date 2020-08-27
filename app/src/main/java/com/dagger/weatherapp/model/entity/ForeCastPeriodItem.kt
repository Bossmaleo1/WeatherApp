package com.dagger.weatherapp.model.entity

data class ForeCastPeriodItem (
    val number : Int?,
    val name : String?,
    val startTime : String?,
    val endTime : String?,
    val isDaytime: Boolean?,
    val temperature: Int?,
    val temperatureUnit : String?,
    val temperatureTrend: String?,
    val windSpeed: String?,
    val windDirection: String?,
    val icon: String?,
    val shortForecast: String?,
    val detailedForecast: String?
)