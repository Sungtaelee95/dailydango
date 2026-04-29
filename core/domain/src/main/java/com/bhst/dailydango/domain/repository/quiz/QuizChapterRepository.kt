package com.bhst.dailydango.domain.repository.quiz

import com.bhst.dailydango.model.result.ChapterResult

interface QuizChapterRepository {
    suspend fun getChapters(): ChapterResult
}