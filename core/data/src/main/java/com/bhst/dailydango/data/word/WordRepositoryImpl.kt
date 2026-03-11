package com.bhst.dailydango.data.word

import com.bhst.dailydango.domain.repository.word.WordRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.WordResult
import com.bhst.dailydango.model.word.WordContent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
) : WordRepository {
    override suspend fun getWordContent(word: String): WordResult {
        return withContext(IO) {
            try {
                val document = fb
                    .collection(COLLECTION_CONTENT_WORD)
                    .document(word)
                    .get()
                    .await()
                if (document.exists()) {
                    val content = document.toObject(WordContent::class.java) ?: throw Exception()
                    WordResult.Success(content)
                } else {
                    WordResult.Error(FbError.ServerError)
                }
            } catch (e: Exception) {
                WordResult.Error(FbError.ServerError)
            }
        }
    }

    companion object {
        private const val COLLECTION_CONTENT_WORD = "content_word"
    }

}