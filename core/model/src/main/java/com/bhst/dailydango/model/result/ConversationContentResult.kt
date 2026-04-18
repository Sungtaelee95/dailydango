package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.conversation.ConversationContent
import com.bhst.dailydango.model.error.FbError

sealed class ConversationContentResult {
    data class Success(val data: List<ConversationContent>) : ConversationContentResult()
    data class Error(val error: FbError) : ConversationContentResult()
}