package com.bhst.dailydango.domain.repository.hanja

import com.bhst.dailydango.model.result.HanjaResult

interface HanjaRepository {
    suspend fun getHanjaContent(hanja: String): HanjaResult
}