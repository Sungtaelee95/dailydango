package com.bhst.dailydango.data.player

import android.net.Uri
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.exoplayer.ExoPlayer
import com.bhst.dailydango.data_source.room.dao.PlayRepeatDao
import com.bhst.dailydango.data_source.room.dao.PlaySpeedDao
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import com.bhst.dailydango.model.play_repeat.PlayRepeatEntity
import com.bhst.dailydango.model.play_speed.PlaySpeedEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayAudioRepositoryImpl @Inject constructor(
    private val exoPlayer: ExoPlayer,
    private val playSpeedDao: PlaySpeedDao,
    private val playRepeatDao: PlayRepeatDao
) : PlayAudioRepository {
    override suspend fun playAudio(uri: Uri) {
        try {
            exoPlayer.stop()
            exoPlayer.clearMediaItems()
            val mediaItem = MediaItem.fromUri(uri)

            val savedSpeed = withContext(IO) {
                playSpeedDao.getPlaySpeed() ?: 1.0f // null일 경우 기본값 1.0f
            }
            val repeatCount = withContext(IO) {
                playRepeatDao.getPlayRepeat() ?: 1
            }

            val mediaItems = List(repeatCount) { mediaItem }

            exoPlayer.setMediaItems(mediaItems)

            // 2. ExoPlayer에 속도 적용하기
            exoPlayer.playbackParameters = PlaybackParameters(savedSpeed)

            exoPlayer.prepare()
            exoPlayer.play()
        } catch (e: Exception) {
            Log.e("lstlst", "오디오 재생 실패: ${e.message}")
        }
    }

    override suspend fun setPlaySpeed(speed: Float) {
        withContext(IO) {
            playSpeedDao.updatePlaySpeed(PlaySpeedEntity(speed = speed))
        }
    }

    override suspend fun setPlayRepeat(repeat: Int) {
        withContext(IO) {
            playRepeatDao.setPlayRepeat(PlayRepeatEntity(repeat = repeat))
        }
    }

    override fun getPlayRepeat(): Flow<Int> {
        return playRepeatDao.getPlayRepeatFlow().map { it ?: 1 }
    }

    override fun getPlaySpeed(): Flow<Float> {
        return playSpeedDao.getPlaySpeedFlow().map { it ?: 1.0f }

    }

    override suspend fun release() {
        exoPlayer.stop()
        exoPlayer.clearMediaItems()

    }
}