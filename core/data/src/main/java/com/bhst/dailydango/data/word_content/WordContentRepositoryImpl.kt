package com.bhst.dailydango.data.word_content

import com.bhst.dailydango.domain.repository.word_content.WordContentRepository
import com.bhst.dailydango.model.content.Content
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.WordContentResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordContentRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
): WordContentRepository {
    override suspend fun getWordContent(chapter: Int): WordContentResult {
        return withContext(IO) {
            try {
                // 경로: chapters/{chapterNumber}/contents
                val snapshot = fb.collection(COLLECTION_CONTENT_WORD)
                    .document(chapter.toString())
                    .collection(SUB_COLLECTION_CONTENT_WORD)
                    .get()
                    .await()

                // 스냅샷 결과를 Content 객체 리스트로 매핑
                val contents = snapshot.documents.mapNotNull { it.toObject(Content::class.java) }

                WordContentResult.Success(contents.sortedBy { it.order })
            } catch (e: Exception) {
                WordContentResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONTENT_WORD = "content_words"
        private const val SUB_COLLECTION_CONTENT_WORD = "content_word"
    }
}