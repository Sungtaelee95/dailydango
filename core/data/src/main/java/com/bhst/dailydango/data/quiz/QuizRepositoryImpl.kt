package com.bhst.dailydango.data.quiz

import com.bhst.dailydango.domain.repository.quiz.QuizRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.quiz.QuizContent
import com.bhst.dailydango.model.result.QuizResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : QuizRepository {
    override suspend fun getQuizContent(chapter: Int): QuizResult {
        return withContext(IO) {
            try {
                // 경로: chapters/{chapterNumber}/contents
                val snapshot = fb.collection(COLLECTION_CONTENT_QUIZ)
                    .document(chapter.toString())
                    .collection(SUB_COLLECTION_CONTENT_QUIZ)
                    .get()
                    .await()

                // 스냅샷 결과를 Content 객체 리스트로 매핑
                val contents =
                    snapshot.documents.mapNotNull { it.toObject(QuizContent::class.java) }

                QuizResult.Success(contents.sortedBy { it.order })
            } catch (e: Exception) {
                QuizResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONTENT_QUIZ = "content_quiz"
        private const val SUB_COLLECTION_CONTENT_QUIZ = "content_quiz"
    }
}