package com.bhst.dailydango.domain.usecase.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationChapterLimitRepository
import javax.inject.Inject

class ConversationChapterLimitUseCase @Inject constructor(
    private val conversationChapterLimitRepository: ConversationChapterLimitRepository
) {
    suspend operator fun invoke() = conversationChapterLimitRepository.getChapterLimit()
}