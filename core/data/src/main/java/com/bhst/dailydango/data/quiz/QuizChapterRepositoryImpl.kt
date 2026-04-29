package com.bhst.dailydango.data.quiz

import com.bhst.dailydango.domain.repository.quiz.QuizChapterRepository
import com.bhst.dailydango.model.chapter.Chapter
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.ChapterResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizChapterRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : QuizChapterRepository {

    override suspend fun getChapters(): ChapterResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection(COLLECTION_QUIZ_CHAPTER).get().await()
                val chapters = snapshot.documents.mapNotNull { it.toObject(Chapter::class.java) }
                ChapterResult.Success(chapters)
            } catch (e: Exception) {
                ChapterResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_QUIZ_CHAPTER = "quiz_chapter"
    }
}