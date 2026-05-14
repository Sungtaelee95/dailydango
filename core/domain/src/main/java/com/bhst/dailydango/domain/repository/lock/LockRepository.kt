package com.bhst.dailydango.domain.repository.lock

import com.bhst.dailydango.model.result.LockResult

interface LockRepository {
    suspend fun getLockState(): LockResult
}