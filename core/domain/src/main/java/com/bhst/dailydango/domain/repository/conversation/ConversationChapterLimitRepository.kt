package com.bhst.dailydango.domain.repository.conversation

import com.bhst.dailydango.model.result.ChapterLimitResult

interface ConversationChapterLimitRepository {
    suspend fun getChapterLimit(): ChapterLimitResult
}