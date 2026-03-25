package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError

sealed class SuggestionResult {
    data class Success(val message: String) : SuggestionResult()
    data class Error(val message: FbError) : SuggestionResult()
}