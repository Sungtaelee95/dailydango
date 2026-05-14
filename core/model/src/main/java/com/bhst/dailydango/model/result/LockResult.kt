package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.lock.Lock

sealed class LockResult {
    data class Success(val data: Lock): LockResult()
    data class Error(val data: FbError): LockResult()
}