package com.bhst.dailydango.data.sound_uri

import android.net.Uri
import com.bhst.dailydango.domain.repository.sound_uri.SoundUriRepository
import com.bhst.dailydango.model.error.FbError
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SoundUriRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
): SoundUriRepository {
    override suspend fun getSoundUri(fileName: String): Uri? {
        try {
            val audioFileName = fileName.replace("?", "-") + ".mp3"
            val storageRef = storage.reference.child(audioFileName)
            return storageRef.downloadUrl.await()
        } catch (e: Exception) {
            return null
        }
    }
}