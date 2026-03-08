package com.bhst.dailydango.domain.usecase.favorite

import com.bhst.dailydango.domain.repository.favorite.FavoriteRepository
import com.bhst.dailydango.model.content.ContentState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesContentUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.favoritesContent().map {
         it.map { content ->
            ContentState.from(content = content)
        }
    }
}