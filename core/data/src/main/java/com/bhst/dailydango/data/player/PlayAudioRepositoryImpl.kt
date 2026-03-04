package com.bhst.dailydango.data.player

import android.net.Uri
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlayAudioRepositoryImpl @Inject constructor(
    private val exoPlayer: ExoPlayer
) : PlayAudioRepository {
    override suspend fun playAudio(uri: Uri) {
        try {
            exoPlayer.stop()
            exoPlayer.clearMediaItems()
            val mediaItem = MediaItem.fromUri(uri)
            exoPlayer.setMediaItem(mediaItem)
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