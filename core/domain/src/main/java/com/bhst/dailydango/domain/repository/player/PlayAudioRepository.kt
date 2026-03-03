package com.bhst.dailydango.domain.repository.player

interface PlayAudioRepository {
    suspend fun playAudio(fileName: String)

    suspend fun release()
}