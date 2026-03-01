package com.bhst.dailydango.domain.repository.sentence_content

import com.bhst.dailydango.model.result.SentenceContentResult

interface SentenceContentRepository {
    suspend fun getSentenceContent(chapter: Int): SentenceContentResult
}