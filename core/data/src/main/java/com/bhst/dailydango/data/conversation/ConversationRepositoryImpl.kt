package com.bhst.dailydango.data.conversation

import com.bhst.dailydango.domain.repository.conversation.ConversationRepository
import com.bhst.dailydango.model.conversation.ConversationContent
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.ConversationContentResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
    private val storage: FirebaseStorage
): ConversationRepository{
    override suspend fun getChapterConversationContents(chapter: Int): ConversationContentResult {
        return withContext(IO) {
            try {
                // 경로: chapters/{chapterNumber}/contents
                val snapshot = fb.collection(COLLECTION_CONTENT_CONVERSATION)
                    .document(chapter.toString())
                    .collection(SUB_COLLECTION_CONTENT_CONVERSATION)
                    .get()
                    .await()

                // 스냅샷 결과를 Content 객체 리스트로 매핑
                val contents =
                    snapshot.documents.mapNotNull { it.toObject(ConversationContent::class.java) }

                ConversationContentResult.Success(contents.sortedBy { it.order })
            } catch (e: Exception) {
                ConversationContentResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONTENT_CONVERSATION = "content_conversation"
        private const val SUB_COLLECTION_CONTENT_CONVERSATION = "content_conversation"
    }
}