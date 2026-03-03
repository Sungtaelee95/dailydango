package com.bhst.dailydango.data.player

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlayAudioRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val exoPlayer: ExoPlayer
) : PlayAudioRepository {
    override suspend fun playAudio(fileName: String) {
        try {
            Log.d("lstlst", "fileName: $fileName")
            val audioFileName = fileName.replace("?", "-") + ".mp3"
            Log.d("lstlst", "audioFileName: $audioFileName")
            val storageRef = storage.reference.child(audioFileName)
            val uri = storageRef.downloadUrl.await()
            exoPlayer.stop()
            exoPlayer.clearMediaItems()

            val mediaItem = MediaItem.fromUri(uri)
            exoPlayer.setMediaItem(mediaItem)

            // 준비 및 재생
            Log.d("lstlst", "파일")
            exoPlayer.prepare()
            exoPlayer.play()
        } catch (e: Exception) {
            Log.e("lstlst", "오디오 재생 실패: ${e.message}")
        }

    }

    override suspend fun release() {
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
    }
}