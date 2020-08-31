package com.dagger.weatherapp.framework.model.entity

import com.google.gson.annotations.SerializedName

data class ApiMainEntity (
    @SerializedName("properties")
    val properties : PropertiesMainEntity
)