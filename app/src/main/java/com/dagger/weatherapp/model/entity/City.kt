package com.dagger.weatherapp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (
    val id: Int?,
    val cityName : String?,
    val lat: Number?,
    val long: Number?
): Parcelable