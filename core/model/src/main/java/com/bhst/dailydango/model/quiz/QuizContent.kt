package com.bhst.dailydango.model.quiz

import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

@Serializable
data class QuizContent(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val subTitle: String = "",
    val soundName: String = "",
    val options: List<QuizOption> = emptyList(),
    val order: Int = 1
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
