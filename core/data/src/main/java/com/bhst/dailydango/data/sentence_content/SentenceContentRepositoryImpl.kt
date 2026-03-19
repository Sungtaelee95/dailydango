package com.bhst.dailydango.data.sentence_content

import com.bhst.dailydango.domain.repository.sentence_content.SentenceContentRepository
import com.bhst.dailydango.model.content.Content
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.SentenceContentResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SentenceContentRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore
) : SentenceContentRepository {
    override suspend fun getSentenceContent(chapter: Int): SentenceContentResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection(COLLECTION_CONTENT_SENTENCE)
                    .document(chapter.toString())
                    .collection(SUB_COLLECTION_CONTENT_SENTENCE)
                    .get()
                    .await()

                // 스냅샷 결과를 Content 객체 리스트로 매핑
                val contents = snapshot.documents.mapNotNull { document ->
                    document.toObject(Content::class.java)?.copy(id = document.id)
                }

                SentenceContentResult.Success(contents.sortedBy { it.order })
            } catch (e: Exception) {
                SentenceContentResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONTENT_SENTENCE = "content_sentences"
        private const val SUB_COLLECTION_CONTENT_SENTENCE = "content_sentence"
    }
}