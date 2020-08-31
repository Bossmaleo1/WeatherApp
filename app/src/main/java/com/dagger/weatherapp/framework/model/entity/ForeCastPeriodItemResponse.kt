package com.dagger.weatherapp.framework.model.entity

import com.google.gson.annotations.SerializedName

data class ForeCastPeriodItemResponse (
    @SerializedName("type")
    val type : String,
    @SerializedName("properties")
    val properties : PropertiesEntity

)