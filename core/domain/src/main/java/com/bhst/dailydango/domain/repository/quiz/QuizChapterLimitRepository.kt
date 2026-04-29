package com.bhst.dailydango.domain.repository.quiz

import com.bhst.dailydango.model.result.ChapterLimitResult

interface QuizChapterLimitRepository {
    suspend fun getChapterLimit(): ChapterLimitResult
}