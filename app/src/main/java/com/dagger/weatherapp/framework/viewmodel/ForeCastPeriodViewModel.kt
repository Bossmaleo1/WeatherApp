package com.dagger.weatherapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity

class ForeCastPeriodViewModel : ViewModel() {

    val foreCastPeriodeList = MutableLiveData<List<ForeCastPeriodItemEntity>>()

    init {

        foreCastPeriodeList.value = arrayListOf(
            ForeCastPeriodItemEntity(
                4, "Friday Night",
                "2020-08-28T18:00:00-04:00", "2020-08-29T06:00:00-04:00",
                false, 76, "F",
                null, "8 mph", "SW",
                "https://api.weather.gov/icons/land/night/tsra,30?size=medium",
                "Chance Showers And Thunderstorms",
                "A chance of showers and thunderstorms. Mostly cloudy, with a low around 76. Southwest wind around 8 mph. Chance of precipitation is 30%. New rainfall amounts less than a tenth of an inch possible."
            )
        )
    }
}