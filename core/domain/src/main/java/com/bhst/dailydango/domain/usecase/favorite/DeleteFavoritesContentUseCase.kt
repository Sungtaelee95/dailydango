package com.bhst.dailydango.domain.usecase.favorite

import com.bhst.dailydango.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class DeleteFavoritesContentUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: String) {
        favoriteRepository.deleteFavoriteContent(id)
    }
}