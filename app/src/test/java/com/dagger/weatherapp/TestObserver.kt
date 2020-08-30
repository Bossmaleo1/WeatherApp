package com.dagger.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


class TestObserver<T> : Observer<T> {


    val observedValues = mutableListOf<T?>()

    override fun onChanged(t: T) {
        observedValues.add(t)
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().apply {
     observeForever(this)
}