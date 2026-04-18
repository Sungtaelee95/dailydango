package com.bhst.dailydango.domain.repository.conversation

import com.bhst.dailydango.model.result.ChapterResult

interface ConversationChapterRepository {
    suspend fun  getChapters(): ChapterResult
}