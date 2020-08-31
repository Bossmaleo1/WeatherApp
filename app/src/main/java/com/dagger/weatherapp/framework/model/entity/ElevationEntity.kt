package com.dagger.weatherapp.framework.model.entity

import com.google.gson.annotations.SerializedName

data class ElevationEntity(
    @SerializedName("value")
    val value: Double,
    @SerializedName("unitCode")
    val unitCode: String
)