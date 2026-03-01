package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.content.Content
import com.bhst.dailydango.model.error.FbError

sealed class WordContentResult {
    data class Success(val contents: List<Content>) : WordContentResult()
    data class Error(val error: FbError) : WordContentResult()
}