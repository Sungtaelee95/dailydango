package com.bhst.dailydango.domain.usecase.player

import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class AudioRepeatUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    operator fun invoke() = playAudioRepository.getPlayRepeat()
}