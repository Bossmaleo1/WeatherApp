package com.dagger.weatherapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.weatherapp.framework.model.entity.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ListCityViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val cities = MutableLiveData<List<City>>()

    init {
        //we build our towns list
        cities.value = arrayListOf(
            City(
                0,
                "New York",
                40.712776,
                -74.005974
            ),
            City(
                1,
                "Chicago",
                41.878113,
                -87.629799
            ),
            City(
                2,
                "San Francisco",
                37.774929,
                -122.419418
            ),
            City(
                3,
                "Miami",
                25.761681,
                -80.191788
            )
        )

        val saved =  MutableLiveData<Boolean>()
        val currentForeCastPeriodItem = MutableLiveData<ForeCastPeriodItem?>()

        //fun
    }
}