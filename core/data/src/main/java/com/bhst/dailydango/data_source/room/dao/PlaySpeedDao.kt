package com.bhst.dailydango.data_source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhst.dailydango.model.play_speed.PlaySpeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaySpeedDao {
    @Query("SELECT speed FROM play_speed WHERE id = 1")
    fun getPlaySpeedFlow(): Flow<Float?>

    @Query("SELECT speed FROM play_speed WHERE id = 1")
    fun getPlaySpeed(): Float?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlaySpeed(playSpeedEntity: PlaySpeedEntity)
}