package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError

sealed class FbResult {
    data class Success<T>(val data: T) : FbResult()
    data class Error(val error: FbError) : FbResult()
}
