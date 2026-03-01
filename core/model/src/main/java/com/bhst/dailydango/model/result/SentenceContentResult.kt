package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.content.Content
import com.bhst.dailydango.model.error.FbError

sealed class SentenceContentResult {
    data class Success(val contents: List<Content>) : SentenceContentResult()
    data class Error(val error: FbError) : SentenceContentResult()
}