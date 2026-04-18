package com.bhst.dailydango.data.tip

import com.bhst.dailydango.domain.repository.tip.TipRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.TipResult
import com.bhst.dailydango.model.tip.TipDocument
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : TipRepository {
    override suspend fun getChapterTips(chapter: Int): TipResult {
        return withContext(IO) {
            try {
                val documentName = "Chapter_$chapter"
                val snapshot = fb.collection("tips").document(documentName).get().await()
                val tips = snapshot.toObject(TipDocument::class.java)?.tipList ?: emptyList()
                TipResult.Success(tips.sortedBy { it.order })
            } catch (e: Exception) {
                TipResult.Error(FbError.ServerError)
            }
        }
    }

    override suspend fun getHiraganaTips(): TipResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection("tips").document("Hiragana").get().await()
                val tips = snapshot.toObject(TipDocument::class.java)?.tipList ?: emptyList()
                TipResult.Success(tips.sortedBy { it.order })
            } catch (e: Exception) {
                TipResult.Error(FbError.ServerError)
            }
        }
    }

    override suspend fun getKatakanaTips(): TipResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection("tips").document("Katakana").get().await()
                val tips = snapshot.toObject(TipDocument::class.java)?.tipList ?: emptyList()
                TipResult.Success(tips.sortedBy { it.order })
            } catch (e: Exception) {
                TipResult.Error(FbError.ServerError)
            }
        }
    }

    override suspend fun getConversationTips(): TipResult {
        return withContext(IO) {
            try {
                val snapshot = fb.collection("tips").document("Conversation_Tips").get().await()
                val tips = snapshot.toObject(TipDocument::class.java)?.tipList ?: emptyList()
                TipResult.Success(tips.sortedBy { it.order })
            } catch (e: Exception) {
                TipResult.Error(FbError.ServerError)
            }
        }

    }
}