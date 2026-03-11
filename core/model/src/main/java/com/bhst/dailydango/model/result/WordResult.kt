package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.word.WordContent

sealed class WordResult {
    data class Success(val content: WordContent) : WordResult()
    data class Error(val error: FbError) : WordResult()
}