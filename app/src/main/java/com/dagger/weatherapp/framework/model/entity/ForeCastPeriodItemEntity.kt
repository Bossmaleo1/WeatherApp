package com.dagger.weatherapp.framework.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foreCastPeriodItem")
data class ForeCastPeriodItemEntity (
    @ColumnInfo(name = "number")
    val number : Int?,
    @ColumnInfo(name = "name")
    val name : String?,
    @ColumnInfo(name = "startTime")
    val startTime : String?,
    @ColumnInfo(name = "endTime")
    val endTime : String?,
    @ColumnInfo(name = "isDaytime")
    val isDaytime: Boolean?,
    @ColumnInfo(name = "temperature")
    val temperature: Int?,
    @ColumnInfo(name = "temperatureUnit")
    val temperatureUnit : String?,
    @ColumnInfo(name = "temperatureTrend")
    val temperatureTrend: String?,
    @ColumnInfo(name = "windSpeed")
    val windSpeed: String?,
    @ColumnInfo(name = "windDirection")
    val windDirection: String?,
    @ColumnInfo(name = "icon")
    val icon: String?,
    @ColumnInfo(name = "shortForecast")
    val shortForecast: String?,
    @ColumnInfo(name = "detailedForecast")
    val detailedForecast: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}