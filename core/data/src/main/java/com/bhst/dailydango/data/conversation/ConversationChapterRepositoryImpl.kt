package com.bhst.dailydango.data.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationChapterRepository
import com.bhst.dailydango.model.chapter.Chapter
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.ChapterResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConversationChapterRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : ConversationChapterRepository {

    override suspend fun getChapters(): ChapterResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection(COLLECTION_CONVERSATION_CHAPTER).get().await()
                val chapters = snapshot.documents.mapNotNull { it.toObject(Chapter::class.java) }
                ChapterResult.Success(chapters)
            } catch (e: Exception) {
                ChapterResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONVERSATION_CHAPTER = "conversation_chapter"
    }
}