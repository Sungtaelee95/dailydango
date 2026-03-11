package com.bhst.dailydango.domain.usecase.gif_uri

import com.bhst.dailydango.domain.repository.gif.GifRepository
import javax.inject.Inject

class GifUriUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {
    suspend operator fun invoke(fileName: String) = gifRepository.getGifUri(fileName)
}