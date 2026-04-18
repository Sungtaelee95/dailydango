package com.bhst.dailydango.model.conversation

import android.util.Log
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

data class ConversationContentState(
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
    val order: Int = 1, // 정렬을 위해 사용됩니다.
    val isOpen: Boolean = false,
    val contentUri: ConversationContentUri = ConversationContentUri(),
) {
    val isAllLoading: Boolean get() = contentUri.getContentUriList().count() == ttsCount
    companion object {
        fun from(content: ConversationContent): ConversationContentState {
            return ConversationContentState(
                id = content.id,
                titleImageUrl = content.titleImageUrl,
                exampleForJapanese1 = content.exampleForJapanese1,
                explanationForKorean1 = content.explanationForKorean1,
                explanationForKoreanSound1 = content.explanationForKoreanSound1,
                exampleForJapanese2 = content.exampleForJapanese2,
                explanationForKorean2 = content.explanationForKorean2,
                explanationForKoreanSound2 = content.explanationForKoreanSound2,
                exampleForJapanese3 = content.exampleForJapanese3,
                explanationForKorean3 = content.explanationForKorean3,
                explanationForKoreanSound3 = content.explanationForKoreanSound3,
                exampleForJapanese4 = content.exampleForJapanese4,
                explanationForKorean4 = content.explanationForKorean4,
                explanationForKoreanSound4 = content.explanationForKoreanSound4,
                exampleForJapanese5 = content.exampleForJapanese5,
                explanationForKorean5 = content.explanationForKorean5,
                explanationForKoreanSound5 = content.explanationForKoreanSound5,
                exampleForJapanese6 = content.exampleForJapanese6,
                explanationForKorean6 = content.explanationForKorean6,
                explanationForKoreanSound6 = content.explanationForKoreanSound6,
                exampleForJapanese7 = content.exampleForJapanese7,
                explanationForKorean7 = content.explanationForKorean7,
                explanationForKoreanSound7 = content.explanationForKoreanSound7,
                exampleForJapanese8 = content.exampleForJapanese8,
                explanationForKorean8 = content.explanationForKorean8,
                explanationForKoreanSound8 = content.explanationForKoreanSound8,
                exampleForJapanese9 = content.exampleForJapanese9,
                explanationForKorean9 = content.explanationForKorean9,
                explanationForKoreanSound9 = content.explanationForKoreanSound9,
                exampleForJapanese10 = content.exampleForJapanese10,
                explanationForKorean10 = content.explanationForKorean10,
                explanationForKoreanSound10 = content.explanationForKoreanSound10,
                ttsCount = content.ttsCount,
                wordTips = content.wordTips,
            )
        }
    }
}