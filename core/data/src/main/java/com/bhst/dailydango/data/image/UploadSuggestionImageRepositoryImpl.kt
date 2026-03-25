package com.bhst.dailydango.data.image

import android.net.Uri
import com.bhst.dailydango.domain.repository.image.UploadSuggestionImageRepository
import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.result.ImageResult
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UploadSuggestionImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage
): UploadSuggestionImageRepository {
    override suspend fun uploadImage(uri: Uri): ImageResult {
        return withContext(IO) {
            try {
                val fileName = UUID.randomUUID().toString() + ".jpg"
                val storageRef = storage.reference.child("image/suggestion/$fileName")
                storageRef.putFile(uri).await()
                val imageUrl = storageRef.downloadUrl.await().toString()
                ImageResult.Success(imageUrl)
            } catch (e: Exception) {
                ImageResult.Error(FbError.ServerError)
            }
        }
    }

}