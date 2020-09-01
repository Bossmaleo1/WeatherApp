package com.dagger.weatherapp.framework.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dagger.core.data.ForeCastPeriodItem
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foreCastPeriodItem")
data class ForeCastPeriodItemEntity(
    @ColumnInfo(name = "number")
    @PrimaryKey
    @SerializedName("number")
    val number: Int?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "startTime")
    @SerializedName("startTime")
    val startTime: String?,
    @ColumnInfo(name = "endTime")
    @SerializedName("endTime")
    val endTime: String?,
    @ColumnInfo(name = "isDaytime")
    @SerializedName("isDaytime")
    val isDaytime: Boolean?,
    @ColumnInfo(name = "temperature")
    @SerializedName("temperature")
    val temperature: Int?,
    @ColumnInfo(name = "temperatureUnit")
    @SerializedName("temperatureUnit")
    val temperatureUnit: String?,
    @ColumnInfo(name = "temperatureTrend")
    @SerializedName("temperatureTrend")
    val temperatureTrend: String?,
    @ColumnInfo(name = "windSpeed")
    @SerializedName("windSpeed")
    val windSpeed: String?,
    @ColumnInfo(name = "windDirection")
    @SerializedName("windDirection")
    val windDirection: String?,
    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    val icon: String?,
    @ColumnInfo(name = "shortForecast")
    @SerializedName("shortForecast")
    val shortForecast: String?,
    @ColumnInfo(name = "detailedForecast")
    @SerializedName("detailedForecast")
    val detailedForecast: String?
) {


    companion object {

        fun fromForeCastPeriodItem(foreCastPeriodItem: ForeCastPeriodItem) =
            ForeCastPeriodItemEntity(
                foreCastPeriodItem.number,
                foreCastPeriodItem.name,
                foreCastPeriodItem.startTime,
                foreCastPeriodItem.endTime,
                foreCastPeriodItem.isDaytime,
                foreCastPeriodItem.temperature,
                foreCastPeriodItem.temperatureUnit,
                foreCastPeriodItem.temperatureTrend,
                foreCastPeriodItem.windSpeed,
                foreCastPeriodItem.windDirection,
                foreCastPeriodItem.icon,
                foreCastPeriodItem.shortForecast,
                foreCastPeriodItem.detailedForecast
            )

    }

    fun toForeCastPeriodItem() = ForeCastPeriodItem(
        number,
        name,
        startTime,
        endTime,
        isDaytime,
        temperature,
        temperatureUnit,
        temperatureTrend,
        windSpeed,
        windDirection,
        icon,
        shortForecast,
        detailedForecast
    )
}