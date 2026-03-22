package com.bhst.dailydango.data.sound_uri

import android.net.Uri
import android.util.Log
import com.bhst.dailydango.domain.repository.sound_uri.SoundUriRepository
import com.bhst.dailydango.model.error.FbError
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SoundUriRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
): SoundUriRepository {
    override suspend fun getSoundUri(fileName: String): Uri? {
        return withContext(Dispatchers.IO) {
            if (fileName.isEmpty()) return@withContext null
            try {
                val audioFileName = fileName.replace("?", "-") + ".mp3"
                val storageRef = storage.reference.child(audioFileName)
                storageRef.downloadUrl.await()
            } catch (e: Exception) {
                Log.d("lstlst", "못찾은 파일 네임: $fileName")
                null
            }
        }
    }
}