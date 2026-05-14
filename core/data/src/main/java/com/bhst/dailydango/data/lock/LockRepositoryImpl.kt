package com.bhst.dailydango.data.lock

import com.bhst.dailydango.domain.repository.lock.LockRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.lock.Lock
import com.bhst.dailydango.model.result.LockResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LockRepositoryImpl @Inject constructor(
    private val fb: FirebaseFirestore,
): LockRepository {
    override suspend fun getLockState(): LockResult {
        return try {
            val option  = fb.collection(COLLECTION_LOCK)
                .document(COLLECTION_LOCK)
                .get()
                .await()
                .toObject(Lock::class.java)
            if (option != null) {
                LockResult.Success(option)
            } else {
                LockResult.Error(FbError.ServerError)
            }
        } catch (e: Exception) {
            LockResult.Error(FbError.ServerError)
        }
    }

    companion object {
        private const val COLLECTION_LOCK = "app_lock"
    }
}