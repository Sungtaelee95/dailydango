package com.bhst.dailydango.model.quiz

data class QuizContentState(
    val id: String,
    val title: String,
    val subTitle: String,
    val soundName: String,
    val options: List<QuizOptionState>,
    val order: Int
)