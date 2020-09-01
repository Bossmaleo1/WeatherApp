package com.dagger.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dagger.weatherapp.framework.model.entity.City
import com.dagger.weatherapp.framework.viewmodel.ListCityViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ListCityViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun OurListCityViewModel() {
        val viewModel = ListCityViewModel()

        val observer : TestObserver<List<City>> = viewModel.cities.testObserver()

        Assert.assertEquals(arrayListOf(
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
        ),observer.observedValues[0])
    }
}