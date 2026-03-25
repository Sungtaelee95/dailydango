package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError

sealed class ImageResult {
    data class Success(val imageUrl: String) : ImageResult()
    data class Error(val error: FbError) : ImageResult()
}