package com.bhst.dailydango.model.quiz

import android.net.Uri

data class QuizContentState(
    val id: String,
    val title: String,
    val subTitle: String,
    val soundName: String,
    val soundUri: Uri? = null,
    val options: List<QuizOptionState>,
    val order: Int
)