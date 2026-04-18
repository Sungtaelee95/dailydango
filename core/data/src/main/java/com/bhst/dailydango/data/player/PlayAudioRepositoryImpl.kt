package com.bhst.dailydango.data.player

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.SilenceMediaSource
import com.bhst.dailydango.data_source.room.dao.PlayRepeatDao
import com.bhst.dailydango.data_source.room.dao.PlaySpeedDao
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import com.bhst.dailydango.model.play_repeat.PlayRepeatEntity
import com.bhst.dailydango.model.play_speed.PlaySpeedEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayAudioRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
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

    @OptIn(UnstableApi::class)
    override suspend fun playAudios(uris: List<Uri>) {
        try {
            exoPlayer.stop()
            exoPlayer.clearMediaItems()

            // MediaItem 대신 MediaSource를 담을 리스트와 팩토리 준비
            val mediaSources = mutableListOf<MediaSource>()
            val mediaSourceFactory = DefaultMediaSourceFactory(context)

            val savedSpeed = withContext(IO) {
                playSpeedDao.getPlaySpeed() ?: 1.0f // null일 경우 기본값 1.0f
            }
            val repeatCount = withContext(IO) {
                playRepeatDao.getPlayRepeat() ?: 1
            }

            val totalItems = repeatCount * uris.size
            var currentCount = 0

            // 배속이 적용되어도 '실제 시간'으로 0.4초를 보장하기 위해 배속만큼 묵음 길이를 늘려줍니다.
            // (단위: 마이크로초, 0.4초 = 400,000L)
            val silenceDurationUs = (400_000L * savedSpeed).toLong()

            repeat(repeatCount) {
                uris.forEach {
                    currentCount++

                    // 1. 실제 오디오 소스 추가
                    val mediaItem = MediaItem.fromUri(it)
                    mediaSources.add(mediaSourceFactory.createMediaSource(mediaItem))

                    // 2. 마지막 곡이 아닐 경우 0.4초 무음 소스 끼워넣기
                    if (currentCount < totalItems) {
                        mediaSources.add(SilenceMediaSource(silenceDurationUs))
                    }
                }
            }

            // setMediaItems 대신 setMediaSources 사용
            exoPlayer.setMediaSources(mediaSources)

            // ExoPlayer에 속도 적용하기
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