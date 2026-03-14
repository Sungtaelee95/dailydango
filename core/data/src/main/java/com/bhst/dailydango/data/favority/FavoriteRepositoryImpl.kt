package com.bhst.dailydango.data.favority

import com.bhst.dailydango.data_source.room.dao.FavoriteContentDao
import com.bhst.dailydango.domain.repository.favorite.FavoriteRepository
import com.bhst.dailydango.model.room.entity.FavoriteContentEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteContentDao: FavoriteContentDao
) : FavoriteRepository {
    override fun favoritesContent(): Flow<List<FavoriteContentEntity>> {
        return favoriteContentDao.getAllFavoriteContents()
    }

    override suspend fun deleteFavoriteContent(japaneseTitle: String) {
        withContext(IO) {
            favoriteContentDao.deleteFavoriteContent(japaneseTitle)
        }
    }

    override suspend fun insertFavoriteContent(favoriteContent: FavoriteContentEntity) {
        withContext(IO) {
            favoriteContentDao.insertFavoriteContent(favoriteContent)
        }
    }
}