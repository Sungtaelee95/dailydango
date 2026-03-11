package com.bhst.dailydango.data.hanja

import com.bhst.dailydango.domain.repository.hanja.HanjaRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.hanja.HanjaContent
import com.bhst.dailydango.model.result.HanjaResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HanjaRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
): HanjaRepository {
    override suspend fun getHanjaContent(hanja: String): HanjaResult {
        return try {
            val document = fb.collection(COLLECTION_CONTENT_HANJA)
                .document(hanja) // 저장할 때 document ID를 hanja로 했으므로 ID로 바로 검색
                .get()
                .await()

            if (document.exists()) {
                // 문서가 존재하면 HanjaContent 객체로 변환하여 반환
                val content = document.toObject(HanjaContent::class.java) ?: throw Exception()
                HanjaResult.Success(content)
            } else {
                // 문서가 존재하지 않으면 null 반환 (또는 에러 처리 가능)
                HanjaResult.Error(FbError.ServerError)
            }
        } catch (e: Exception) {
            HanjaResult.Error(FbError.ServerError)
        }
    }
    companion object {
        private const val COLLECTION_CONTENT_HANJA = "content_hanja"
    }
}