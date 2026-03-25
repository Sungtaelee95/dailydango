package com.bhst.dailydango.data.suggestion

import com.bhst.dailydango.domain.repository.suggestion.SuggestionRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.SuggestionResult
import com.bhst.dailydango.model.suggestion.Suggestion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore
): SuggestionRepository {
    companion object {
        private const val COLLECTION_SUGGESTION = "suggestion"
    }
    override suspend fun uploadSuggestion(suggestion: Suggestion): SuggestionResult {
        return withContext(IO) {
            try {
                fb.collection(COLLECTION_SUGGESTION)
                    .document()
                    .set(suggestion)
                    .await()
                SuggestionResult.Success("문의가 접수되었습니다.")
            } catch (e: Exception) {
                SuggestionResult.Error(FbError.ServerError)
            }
        }
    }
}