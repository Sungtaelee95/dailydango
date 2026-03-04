package com.bhst.dailydango.domain.usecase.player

import android.net.Uri
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class AudioPlayUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    suspend fun playAudio(uri: Uri) {
        playAudioRepository.playAudio(uri = uri)
    }

    suspend fun release() {
        playAudioRepository.release()
    }
}