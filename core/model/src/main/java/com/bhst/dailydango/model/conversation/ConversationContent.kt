package com.bhst.dailydango.model.conversation

import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

@Serializable
data class ConversationContent(
    @DocumentId
    val id: String = "", // 문서 ID 자동 매핑
    val titleImageUrl: String = "",
    val exampleForJapanese1: String = "",
    val explanationForKorean1: String = "",
    val explanationForKoreanSound1: String = "",
    val exampleForJapanese2: String = "",
    val explanationForKorean2: String = "",
    val explanationForKoreanSound2: String = "",
    val exampleForJapanese3: String = "",
    val explanationForKorean3: String = "",
    val explanationForKoreanSound3: String = "",
    val exampleForJapanese4: String = "",
    val explanationForKorean4: String = "",
    val explanationForKoreanSound4: String = "",
    val exampleForJapanese5: String = "",
    val explanationForKorean5: String = "",
    val explanationForKoreanSound5: String = "",
    val exampleForJapanese6: String = "",
    val explanationForKorean6: String = "",
    val explanationForKoreanSound6: String = "",
    val exampleForJapanese7: String = "",
    val explanationForKorean7: String = "",
    val explanationForKoreanSound7: String = "",
    val exampleForJapanese8: String = "",
    val explanationForKorean8: String = "",
    val explanationForKoreanSound8: String = "",
    val exampleForJapanese9: String = "",
    val explanationForKorean9: String = "",
    val explanationForKoreanSound9: String = "",
    val exampleForJapanese10: String = "",
    val explanationForKorean10: String = "",
    val explanationForKoreanSound10: String = "",
    val wordTips: String = "",
    val ttsCount: Int = 0,
    val order: Int = 1 // 정렬을 위해 사용됩니다.
)