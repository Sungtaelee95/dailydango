package com.bhst.dailydango.model.content

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val id: String = "", // 문서 ID 자동 매핑
    val titleHanja: String = "",
    val japaneseTitle: String = "",
    val japaneseTitleOfSoundToKorea: String = "",
    val partOfSpeech: String = "",
    val titleToKorean: String = "",
    val tip: String = "",
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
    val order: Int = 1 // 정렬을 위해 사용됩니다.
)
