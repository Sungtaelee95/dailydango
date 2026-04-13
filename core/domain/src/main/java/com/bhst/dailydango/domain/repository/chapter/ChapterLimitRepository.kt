package com.bhst.dailydango.domain.repository.chapter

import com.bhst.dailydango.model.result.ChapterLimitResult

interface ChapterLimitRepository {
    suspend fun getChapterLimit(): ChapterLimitResult
}