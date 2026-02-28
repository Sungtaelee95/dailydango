package com.bhst.dailydango.model.error

sealed class FbError {
    data object ServerError: FbError()
}
