package com.dagger.weatherapp.framework.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dagger.weatherapp.framework.model.entity.City
import java.lang.IllegalArgumentException

class ForeCastPeriodViewModelFactory(private val app: Application, private val city: City) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         if(modelClass.isAssignableFrom(ForeCastPeriodViewModel::class.java)) {
             return ForeCastPeriodViewModel(app,city) as T
         }
        throw  IllegalArgumentException("Unknow View Model Class")
    }
}