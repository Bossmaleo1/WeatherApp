package com.dagger.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dagger.weatherapp.model.entity.ForeCastPeriodItem
import com.dagger.weatherapp.viewmodel.ForeCastPeriodViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ForeCastPeriodViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testSidney() {
        val viewModel = ForeCastPeriodViewModel()
        val foreCastPeriodItem = ForeCastPeriodViewModel()
        val observer : TestObserver<List<ForeCastPeriodItem>> = viewModel.foreCastPeriodeList.testObserver()
        /*Assert.assertEquals(arrayListOf(
            ForeCastPeriodItem(4,"Friday Night",
                "2020-08-28T18:00:00-04:00","2020-08-29T06:00:00-04:00",
                false,76,"F",
                null,"8 mph","SW",
                "https://api.weather.gov/icons/land/night/tsra,30?size=medium",
                "Chance Showers And Thunderstorms",
                "A chance of showers and thunderstorms. Mostly cloudy, with a low around 76. Southwest wind around 8 mph. Chance of precipitation is 30%. New rainfall amounts less than a tenth of an inch possible.")
        ),observer.observedValues[0])*/
        assertEquals(
            listOf(
               foreCastPeriodItem),observer.observedValues
        )
    }
}