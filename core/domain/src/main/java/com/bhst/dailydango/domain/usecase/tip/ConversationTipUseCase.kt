package com.bhst.dailydango.domain.usecase.tip

import com.bhst.dailydango.domain.repository.tip.TipRepository
import com.bhst.dailydango.model.result.TipResult
import javax.inject.Inject

class ConversationTipUseCase @Inject constructor(
    private val tipRepository: TipRepository
) {
    suspend operator fun invoke(): TipResult {
        return tipRepository.getConversationTips()
    }
}