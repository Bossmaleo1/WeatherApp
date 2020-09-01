package com.dagger.weatherapp.framework.model.db

import android.content.Context
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.core.repository.ForeCastPeriodItemDataSource
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity

class RoomForeCastPeriodDataSource (context: Context) : ForeCastPeriodItemDataSource {

    val foreCastPeriodItemDAO = DatabaseService.getInstance(context).foreCastPeriodItemDAO()

    override suspend fun add(foreCastPeriodItemEntities: ForeCastPeriodItem)  = foreCastPeriodItemDAO.addForeCastPeriodItem(ForeCastPeriodItemEntity.fromForeCastPeriodItem(foreCastPeriodItemEntities))

    override suspend fun get(id: Long) = foreCastPeriodItemDAO.getForeCastPeriodItemEntity(id)?.toForeCastPeriodItem()


    override suspend fun getAll() = foreCastPeriodItemDAO.getAllForeCastPeriodItemEntities().map { it.toForeCastPeriodItem() }


    override suspend fun remove(foreCastPeriodItem: ForeCastPeriodItem) = foreCastPeriodItemDAO.deleteForeCastPeriodItemEntity(ForeCastPeriodItemEntity.fromForeCastPeriodItem(foreCastPeriodItem))

}