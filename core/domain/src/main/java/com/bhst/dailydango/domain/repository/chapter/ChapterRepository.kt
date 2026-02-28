package com.bhst.dailydango.domain.repository.chapter

import com.bhst.dailydango.model.result.ChapterResult

interface ChapterRepository {
    suspend fun getChapters(): ChapterResult
}