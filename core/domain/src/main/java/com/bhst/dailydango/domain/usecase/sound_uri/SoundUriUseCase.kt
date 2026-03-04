package com.bhst.dailydango.domain.usecase.sound_uri

import com.bhst.dailydango.domain.repository.sound_uri.SoundUriRepository
import javax.inject.Inject

class SoundUriUseCase @Inject constructor(
    private val soundUriRepository: SoundUriRepository
) {
    suspend operator fun invoke(fileName: String) = soundUriRepository.getSoundUri(fileName)
}