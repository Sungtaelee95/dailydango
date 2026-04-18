package com.bhst.dailydango.domain.repository.tip

import com.bhst.dailydango.model.result.TipResult
import com.bhst.dailydango.model.tip.Tip

interface TipRepository {
    suspend fun getChapterTips(chapter: Int): TipResult
    suspend fun getHiraganaTips(): TipResult
    suspend fun getKatakanaTips(): TipResult

    suspend fun getConversationTips(): TipResult
}