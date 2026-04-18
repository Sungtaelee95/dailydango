package com.bhst.dailydango.data.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationChapterLimitRepository
import com.bhst.dailydango.model.chapter.ChapterLimit
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.ChapterLimitResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConversationChapterLimitRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : ConversationChapterLimitRepository {
    override suspend fun getChapterLimit(): ChapterLimitResult {
        return withContext(IO) {
            try {
                // 컬렉션 전체(get)가 아닌, 특정 문서만 타겟팅하여 가져옵니다.
                val document = fb.collection(COLLECTION_LIMIT_CONVERSATION)
                    .document(DOC_LIMIT_CONFIG)
                    .get()
                    .await()

                if (document.exists()) {
                    val chapter = document.getLong("chapter")?.toInt() ?: 0
                    ChapterLimitResult.Success(ChapterLimit(chapter))
                } else {
                    ChapterLimitResult.Error(FbError.ServerError)
                }
            } catch (e: Exception) {
                ChapterLimitResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_LIMIT_CONVERSATION = "limit_conversation"
        private const val DOC_LIMIT_CONFIG = "config"
    }
}