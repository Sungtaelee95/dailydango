package com.bhst.dailydango.model.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizOption(
    val number: Int = 1,
    val label: String = "",
    val correct: Boolean = false,
    val explanation: String = ""
) {
    fun toDomain(): QuizOptionState {
        return QuizOptionState(
            number = number,
            label = label,
            correct = correct,
            explanation = explanation
        )
    }
}
