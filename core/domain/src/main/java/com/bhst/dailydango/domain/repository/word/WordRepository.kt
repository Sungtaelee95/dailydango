package com.bhst.dailydango.domain.repository.word

import com.bhst.dailydango.model.result.WordResult

interface WordRepository {
    suspend fun getWordContent(word: String): WordResult
}