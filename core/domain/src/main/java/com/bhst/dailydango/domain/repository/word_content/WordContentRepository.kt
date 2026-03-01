package com.bhst.dailydango.domain.repository.word_content

import com.bhst.dailydango.model.result.WordContentResult

interface WordContentRepository {
    suspend fun getWordContent(chapter: Int): WordContentResult
}