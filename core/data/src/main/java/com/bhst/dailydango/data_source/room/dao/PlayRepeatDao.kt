package com.bhst.dailydango.data_source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhst.dailydango.model.play_repeat.PlayRepeatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayRepeatDao {
    @Query("SELECT repeat FROM play_repeat WHERE id = 1")
    fun getPlayRepeat(): Int?

    @Query("SELECT repeat FROM play_repeat WHERE id = 1")
    fun getPlayRepeatFlow(): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPlayRepeat(playRepeatEntity: PlayRepeatEntity)
}