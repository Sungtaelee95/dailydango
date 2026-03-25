package com.bhst.dailydango.domain.repository.suggestion

import com.bhst.dailydango.model.result.SuggestionResult
import com.bhst.dailydango.model.suggestion.Suggestion

interface SuggestionRepository {
    suspend fun uploadSuggestion(suggestion: Suggestion): SuggestionResult
}