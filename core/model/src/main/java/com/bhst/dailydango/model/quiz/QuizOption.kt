package com.bhst.dailydango.model.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizOption(
    val number: Int,
    val label: String,
    val correct: Boolean,
    val explanation: String
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
