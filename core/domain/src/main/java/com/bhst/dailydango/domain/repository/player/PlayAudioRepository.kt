package com.bhst.dailydango.domain.repository.player

import android.net.Uri

interface PlayAudioRepository {
    suspend fun playAudio(uri: Uri)

    suspend fun release()
}