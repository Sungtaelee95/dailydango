package com.bhst.dailydango.domain.usecase.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationChapterRepository
import javax.inject.Inject

class ConversationChapterUseCase @Inject constructor(
    private val conversationChapterRepository: ConversationChapterRepository
) {
    suspend operator fun invoke() = conversationChapterRepository.getChapters()
}