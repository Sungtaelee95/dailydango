package com.bhst.dailydango.domain.usecase.player

import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class AudioPlayUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    suspend fun playAudio(fileName: String) {
        playAudioRepository.playAudio(fileName = fileName)
    }

    suspend fun release() {
        playAudioRepository.release()
    }
}