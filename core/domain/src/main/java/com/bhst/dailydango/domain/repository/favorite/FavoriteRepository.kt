package com.bhst.dailydango.domain.repository.favorite

import com.bhst.dailydango.model.room.entity.FavoriteContentEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun favoritesContent(): Flow<List<FavoriteContentEntity>>

    suspend fun deleteFavoriteContent(japaneseTitle: String)

    suspend fun insertFavoriteContent(favoriteContent: FavoriteContentEntity)
}