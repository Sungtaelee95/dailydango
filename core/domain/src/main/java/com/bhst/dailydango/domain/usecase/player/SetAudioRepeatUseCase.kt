package com.bhst.dailydango.domain.usecase.player

import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class SetAudioRepeatUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    suspend fun setPlayRepeat(repeat: Int) {
        playAudioRepository.setPlayRepeat(repeat)
    }
}