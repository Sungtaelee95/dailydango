package com.bhst.dailydango.domain.usecase.player

import android.net.Uri
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class AudiosPlayUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    suspend fun playAudios(uris: List<Uri>) {
        playAudioRepository.playAudios(uris = uris)
    }

    suspend fun release() {
        playAudioRepository.release()
    }
}