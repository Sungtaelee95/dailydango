package com.bhst.dailydango.model.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizContent(
    val id: String,
    val title: String,
    val subTitle: String,
    val soundName: String,
    val options: List<QuizOption>,
    val order: Int
) {
    fun toDomain(): QuizContentState {
        return QuizContentState(
            id = id,
            title = title,
            subTitle = subTitle,
            soundName = soundName,
            options = options.map { it.toDomain() },
            order = order
        )
    }
}
