package com.bhst.dailydango.domain.usecase.tip

import com.bhst.dailydango.domain.repository.tip.TipRepository
import javax.inject.Inject

class HiraganaTipUseCase @Inject constructor(
    private val tipRepository: TipRepository
) {
    suspend operator fun invoke() = tipRepository.getHiraganaTips()
}