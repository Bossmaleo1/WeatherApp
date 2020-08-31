package com.dagger.weatherapp.framework.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (
    val id: Int?,
    val cityName : String?,
    val lat: Double,
    val long: Double,
    val gridX: Int?,
    val gridY: Int?
): Parcelable