package com.bhst.dailydango.domain.usecase.suggestion

import com.bhst.dailydango.domain.repository.suggestion.SuggestionRepository
import com.bhst.dailydango.model.result.SuggestionResult
import com.bhst.dailydango.model.suggestion.Suggestion
import javax.inject.Inject

class SuggestionUseCase @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) {
    suspend operator fun invoke(suggestion: Suggestion): SuggestionResult {
        return suggestionRepository.uploadSuggestion(suggestion)
    }
}