package com.bhst.dailydango.domain.usecase.image.suggestion

import android.net.Uri
import com.bhst.dailydango.domain.repository.image.UploadSuggestionImageRepository
import com.bhst.dailydango.model.result.ImageResult
import javax.inject.Inject

class SuggestionImageUploadUseCase @Inject constructor(
    private val uploadSuggestionImageRepository: UploadSuggestionImageRepository
) {
    suspend operator fun invoke(uri: Uri): ImageResult {
        return uploadSuggestionImageRepository.uploadImage(uri)
    }
}