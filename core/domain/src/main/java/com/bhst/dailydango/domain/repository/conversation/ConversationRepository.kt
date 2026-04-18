package com.bhst.dailydango.domain.repository.conversation

import com.bhst.dailydango.model.result.ConversationContentResult

interface ConversationRepository {
    suspend fun getChapterConversationContents(chapter: Int): ConversationContentResult
}