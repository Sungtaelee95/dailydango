package com.bhst.dailydango.domain.usecase.quiz

import com.bhst.dailydango.domain.repository.quiz.QuizChapterRepository
import javax.inject.Inject

class QuizChapterUseCase @Inject constructor(
    private val quizChapterRepository: QuizChapterRepository
) {
    suspend operator fun invoke() = quizChapterRepository.getChapters()
}