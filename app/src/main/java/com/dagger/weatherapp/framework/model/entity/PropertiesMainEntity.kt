package com.dagger.weatherapp.framework.model.entity

import com.google.gson.annotations.SerializedName

data class PropertiesMainEntity (
    @SerializedName("forecast")
    val forecast : String
)