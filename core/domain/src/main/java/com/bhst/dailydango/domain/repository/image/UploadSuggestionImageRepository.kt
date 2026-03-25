package com.bhst.dailydango.domain.repository.image

import android.net.Uri
import com.bhst.dailydango.model.result.ImageResult

interface UploadSuggestionImageRepository {
    suspend fun uploadImage(uri: Uri): ImageResult
}