package com.bhst.dailydango.domain.usecase.quiz

import com.bhst.dailydango.domain.repository.quiz.QuizChapterLimitRepository
import javax.inject.Inject

class QuizChapterLimitUseCase @Inject constructor(
    private val quizChapterLimitRepository: QuizChapterLimitRepository
) {
    suspend operator fun invoke() = quizChapterLimitRepository.getChapterLimit()
}