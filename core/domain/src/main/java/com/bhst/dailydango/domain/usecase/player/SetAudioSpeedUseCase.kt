package com.bhst.dailydango.domain.usecase.player

import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import javax.inject.Inject

class SetAudioSpeedUseCase @Inject constructor(
    private val playAudioRepository: PlayAudioRepository
) {
    suspend operator fun invoke(speed: Float) {
        playAudioRepository.setPlaySpeed(speed)
    }
}