package com.bhst.dailydango.domain.usecase.hanja

import com.bhst.dailydango.domain.repository.hanja.HanjaRepository
import javax.inject.Inject

class HanjaUseCase @Inject constructor(
    private val hanjaRepository: HanjaRepository
) {
    suspend operator fun invoke(hanja: String) = hanjaRepository.getHanjaContent(hanja)
}