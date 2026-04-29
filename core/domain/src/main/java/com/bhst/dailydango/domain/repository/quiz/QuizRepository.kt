package com.bhst.dailydango.domain.repository.quiz

import com.bhst.dailydango.model.result.QuizResult

interface QuizRepository {
    suspend fun getQuizContent(chapter: Int): QuizResult
}