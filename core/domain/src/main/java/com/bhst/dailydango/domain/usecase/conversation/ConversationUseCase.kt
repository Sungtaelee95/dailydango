package com.bhst.dailydango.domain.usecase.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationRepository
import javax.inject.Inject

class ConversationUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(chapter: Int) = conversationRepository.getChapterConversationContents(chapter = chapter)
}