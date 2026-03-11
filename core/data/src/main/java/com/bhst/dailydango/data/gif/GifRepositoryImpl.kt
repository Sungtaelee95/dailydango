package com.bhst.dailydango.data.gif

import android.net.Uri
import com.bhst.dailydango.domain.repository.gif.GifRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
): GifRepository {
    override suspend fun getGifUri(fileName: String): Uri? {
        return withContext(IO) {
            try {
                val gifFileName = "gif/$fileName.gif"
                val storageRef = storage.reference.child(gifFileName)
                storageRef.downloadUrl.await()
            } catch (e: Exception) {
                null
            }
        }
    }
}