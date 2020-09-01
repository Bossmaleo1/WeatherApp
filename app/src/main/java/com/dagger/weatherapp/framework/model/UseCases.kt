package com.dagger.weatherapp.framework.model

import com.dagger.core.usecase.AddForeCastPeriodItem
import com.dagger.core.usecase.GetAllForeCastPeriodItem
import com.dagger.core.usecase.GetForeCastPeriodItem
import com.dagger.core.usecase.RemoveForeCastPeriodItem

data class UseCases (
    val addForeCastPeriodItem : AddForeCastPeriodItem,
    val getAllForeCastPeriodItem : GetAllForeCastPeriodItem,
    val getForeCastPeriodItem : GetForeCastPeriodItem,
    val removeForeCastPeriodItem: RemoveForeCastPeriodItem
)