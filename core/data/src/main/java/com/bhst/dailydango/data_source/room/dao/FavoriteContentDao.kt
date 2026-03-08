package com.bhst.dailydango.data_source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhst.dailydango.model.room.entity.FavoriteContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteContentDao {
    @Query("SELECT * FROM favorite_content")
    fun getAllFavoriteContents(): Flow<List<FavoriteContentEntity>>

    @Query("DELETE FROM favorite_content WHERE japaneseTitle = :japaneseTitle")
    suspend fun deleteFavoriteContent(japaneseTitle: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteContent(favoriteContent: FavoriteContentEntity)

}