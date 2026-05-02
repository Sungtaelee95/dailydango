package com.bhst.dailydango.domain.usecase.quiz

import com.bhst.dailydango.domain.repository.quiz.QuizRepository
import javax.inject.Inject

class QuizUseCase @Inject constructor(
    private val quizRepository: QuizRepository
){
    suspend operator fun invoke(chapter: Int) = quizRepository.getQuizContent(chapter)
}