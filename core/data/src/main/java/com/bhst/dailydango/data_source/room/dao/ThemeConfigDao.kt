package com.bhst.dailydango.data_source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhst.dailydango.model.theme.config.ThemeConfig
import com.bhst.dailydango.model.theme.entity.ThemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeConfigDao {
    @Query("SELECT themeConfig FROM theme WHERE id = 1")
    fun getAppThemeConfig(): Flow<ThemeConfig?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAppThemeConfig(themeEntity: ThemeEntity)
}