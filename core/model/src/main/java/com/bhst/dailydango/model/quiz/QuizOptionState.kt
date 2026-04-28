package com.bhst.dailydango.model.quiz

data class QuizOptionState(
    val number: Int,
    val label: String,
    val correct: Boolean,
    val explanation: String,
    val isOpen: Boolean = false
)
