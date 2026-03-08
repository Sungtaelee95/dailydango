package com.bhst.dailydango.domain.usecase.favorite

import com.bhst.dailydango.domain.repository.favorite.FavoriteRepository
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.room.entity.FavoriteContentEntity
import jakarta.inject.Inject

class SetFavoritesContentUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(contentState: ContentState) {
        favoriteRepository.insertFavoriteContent(FavoriteContentEntity.from(contentState))
    }
}