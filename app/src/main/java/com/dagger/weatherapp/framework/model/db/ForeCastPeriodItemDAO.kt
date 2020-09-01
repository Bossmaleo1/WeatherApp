package com.dagger.weatherapp.framework.model.db

import androidx.room.*
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity


@Dao
interface ForeCastPeriodItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addForeCastPeriodItem(foreCastPeriodItemEntity: ForeCastPeriodItemEntity)

    @Query("SELECT * FROM foreCastPeriodItem WHERE number = :id")
    suspend fun getForeCastPeriodItemEntity(id : Long): ForeCastPeriodItemEntity?

    @Query("SELECT * FROM foreCastPeriodItem")
    suspend fun getAllForeCastPeriodItemEntities() : List<ForeCastPeriodItemEntity>

    @Delete
    suspend fun deleteForeCastPeriodItemEntity(foreCastPeriodItemEntity: ForeCastPeriodItemEntity)
}