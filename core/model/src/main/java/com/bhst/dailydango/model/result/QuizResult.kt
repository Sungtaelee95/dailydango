package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.quiz.QuizContent

sealed class QuizResult {
    data class Success(val result: List<QuizContent>): QuizResult()
    data class Error(val result: FbError): QuizResult()
}