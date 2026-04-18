package com.bhst.dailydango.domain.repository.player

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface PlayAudioRepository {
    suspend fun playAudio(uri: Uri)

    suspend fun playAudios(uris: List<Uri>)

    suspend fun setPlaySpeed(speed: Float)

    suspend fun setPlayRepeat(repeat: Int)

    fun getPlayRepeat(): Flow<Int>

    fun getPlaySpeed(): Flow<Float>

    suspend fun release()
}